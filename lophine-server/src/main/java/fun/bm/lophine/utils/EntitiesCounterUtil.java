package fun.bm.lophine.utils;

import ca.spottedleaf.moonrise.common.list.ReferenceList;
import ca.spottedleaf.moonrise.common.misc.PositionCountingAreaMap;
import fun.bm.lophine.config.modules.experiment.GlobalEntitiesCounter;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LocalMobCapCalculator;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.PotentialCalculator;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.NaturalSpawner.getRoughBiome;

public class EntitiesCounterUtil {
    private static final WeakHashMap<ServerLevel, WeakHashMap<Integer, ReferenceList<Entity>>> globalLoadedEntities = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, Object2IntOpenHashMap<MobCategory>> mobsMap = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, WeakHashMap<Integer, PositionCountingAreaMap<ServerPlayer>>> mobsAreaMap = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, Integer> spawnableChunkCount = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, CompletableFuture<Void>> tasks = new WeakHashMap<>();
    private static final Set<Integer> UniqueIds = new HashSet<>();

    private static int lastUsedId = 0;

    public static int generateUniqueId() {
        synchronized (UniqueIds) {
            int id = lastUsedId;
            while (UniqueIds.contains(id)) {
                id++;
            }

            lastUsedId = id;
            UniqueIds.add(id);
            return id;
        }
    }

    public static void onWorldDataUnload(ServerLevel level, int uniqueId) {
        synchronized (UniqueIds) {
            UniqueIds.remove(uniqueId);
        }
        synchronized (globalLoadedEntities) {
            globalLoadedEntities.get(level).remove(uniqueId);
        }
        synchronized (mobsAreaMap) {
            mobsAreaMap.get(level).remove(uniqueId);
        }
    }

    public static void addDataToLoaded(ServerLevel level, ReferenceList<Entity> data, int uniqueId) {
        WeakHashMap<Integer, ReferenceList<Entity>> data0 = globalLoadedEntities.computeIfAbsent(level, k -> new WeakHashMap<>());
        if (data0.containsKey(uniqueId)) return;
        data0.put(uniqueId, data);
    }

    public static void reportAreaMap(ServerLevel level, PositionCountingAreaMap<ServerPlayer> areaMap, int uniqueId) {
        WeakHashMap<Integer, PositionCountingAreaMap<ServerPlayer>> areaMap0 = mobsAreaMap.computeIfAbsent(level, k -> new WeakHashMap<>());
        if (areaMap0.containsKey(uniqueId)) return;
        areaMap0.put(uniqueId, areaMap);
    }

    public static int getTotalChunkCount(ServerLevel level) {
        return spawnableChunkCount.getOrDefault(level, 0);
    }

    public static @Nullable Object2IntOpenHashMap<MobCategory> getMobsMap(ServerLevel level) {
        return mobsMap.get(level);
    }

    public static boolean canRunNewTask(ServerLevel level) {
        CompletableFuture<Void> task = tasks.get(level);
        return task == null || task.isDone();
    }

    public static void tick(ServerLevel level) {
        Runnable task = () -> {
            WeakHashMap<Integer, ReferenceList<Entity>> data0 = globalLoadedEntities.get(level);
            Object2IntOpenHashMap<MobCategory> map = new Object2IntOpenHashMap<>();
            for (ReferenceList<Entity> data : data0.values()) {
                for (Entity entity : GlobalEntitiesCounter.enabled ? data.copy() : data) {
                    if (entity == null || entity.isRemoved() || !entity.isAlive()) continue;
                    // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
                    MobCategory category = entity.getType().getCategory();
                    if (category != MobCategory.MISC) {
                        // Paper start - Only count natural spawns
                        if (!entity.level().paperConfig().entities.spawning.countAllMobsForSpawning &&
                                !(entity.spawnReason == CreatureSpawnEvent.SpawnReason.NATURAL ||
                                        entity.spawnReason == CreatureSpawnEvent.SpawnReason.CHUNK_GEN)) {
                            continue;
                        }
                        // Paper end - Only count natural spawns
                        map.addTo(category, 1);
                    }
                    // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
                }
            }
            mobsMap.put(level, map);
            for (ServerLevel world : mobsAreaMap.keySet()) {
                int count = 0;
                WeakHashMap<Integer, PositionCountingAreaMap<ServerPlayer>> collection = mobsAreaMap.get(level);
                if (collection == null) continue;
                for (PositionCountingAreaMap<ServerPlayer> areaMap : collection.values()) {
                    count += areaMap.getTotalPositions();
                }
                spawnableChunkCount.put(world, count);
            }
        };
        if (GlobalEntitiesCounter.async) {
            tasks.put(level, CompletableFuture.runAsync(task));
        } else {
            task.run();
        }
    }

    public static NaturalSpawner.SpawnState runRemainingTasks(
            ServerLevel level, Iterable<Entity> entities, NaturalSpawner.ChunkGetter chunkGetter, LocalMobCapCalculator calculator, final boolean countMobs
    ) {
        Object2IntOpenHashMap<MobCategory> map = getMobsMap(level);
        if (map == null) return null; // skip if no data
        // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
        PotentialCalculator potentialCalculator = new PotentialCalculator();
        for (Entity entity : entities) {
            if (entity == null || entity.isRemoved() || !entity.isAlive()) continue;
            // Paper start - Only count natural spawns
            if (!entity.level().paperConfig().entities.spawning.countAllMobsForSpawning &&
                    !(entity.spawnReason == CreatureSpawnEvent.SpawnReason.NATURAL ||
                            entity.spawnReason == CreatureSpawnEvent.SpawnReason.CHUNK_GEN)) {
                continue;
            }
            // Paper end - Only count natural spawns
            BlockPos blockPos = entity.blockPosition();
            chunkGetter.query(ChunkPos.asLong(blockPos), chunk -> {
                MobSpawnSettings.MobSpawnCost mobSpawnCost = getRoughBiome(blockPos, chunk).getMobSettings().getMobSpawnCost(entity.getType());
                if (mobSpawnCost != null) {
                    potentialCalculator.addCharge(entity.blockPosition(), mobSpawnCost.charge());
                }

                if (calculator != null && entity instanceof Mob) { // Paper - Optional per player mob spawns
                    calculator.addMob(chunk.getPos(), entity.getType().getCategory());
                }

                // Paper start - Optional per player mob spawns
                if (countMobs) {
                    chunk.level.getChunkSource().chunkMap.updatePlayerMobTypeMap(entity);
                }
                // Paper end - Optional per player mob spawns
            });
        }
        return new NaturalSpawner.SpawnState(getTotalChunkCount(level), map, potentialCalculator, calculator);
        // Lophine end - Copy from net/minecraft/world/level/NaturalSpawner
    }
}
