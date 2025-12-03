package fun.bm.lophine.utils;

import ca.spottedleaf.moonrise.common.list.ReferenceList;
import fun.bm.lophine.config.modules.experiment.GlobalEntitiesCounter;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LocalMobCapCalculator;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.PotentialCalculator;
import net.minecraft.world.level.biome.MobSpawnSettings;
import org.bukkit.craftbukkit.util.WeakCollection;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.Nullable;

import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.NaturalSpawner.getRoughBiome;

public class EntitiesCounterUtil {
    private static final WeakHashMap<ServerLevel, WeakCollection<ReferenceList<Entity>>> globalLoadedEntities = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, Object2IntOpenHashMap<MobCategory>> mobsMap = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, CompletableFuture<Void>> tasks = new WeakHashMap<>();

    public static void addDataToLoaded(ReferenceList<Entity> data, ServerLevel level) {
        WeakCollection<ReferenceList<Entity>> data0 = globalLoadedEntities.computeIfAbsent(level, k -> new WeakCollection<>());
        data0.add(data);
    }

    public static @Nullable Object2IntOpenHashMap<MobCategory> getMobsMap(ServerLevel level) {
        return mobsMap.get(level);
    }

    public static boolean canRunNewTask(ServerLevel level) {
        return tasks.get(level).isDone();
    }

    public static void tick(ServerLevel level) {
        Runnable task = () -> {
            WeakCollection<ReferenceList<Entity>> data0 = globalLoadedEntities.get(level);
            Object2IntOpenHashMap<MobCategory> map = new Object2IntOpenHashMap<>();
            for (ReferenceList<Entity> data : data0) {
                for (Entity entity : GlobalEntitiesCounter.enabled ? data.copy() : data) {
                    // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
                    MobCategory category = entity.getType().getCategory();
                    if (category != MobCategory.MISC) {
                        // Paper start - Only count natural spawns
                        if (!entity.level().paperConfig().entities.spawning.countAllMobsForSpawning &&
                                !(entity.spawnReason == CreatureSpawnEvent.SpawnReason.NATURAL ||
                                        entity.spawnReason == CreatureSpawnEvent.SpawnReason.CHUNK_GEN)) {
                            continue;
                        }
                        map.addTo(category, 1);
                        // Paper end - Only count natural spawns
                    }
                    // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
                }
            }
            mobsMap.put(level, map);
        };
        if (GlobalEntitiesCounter.async) {
            tasks.put(level, CompletableFuture.runAsync(task));
        } else {
            task.run();
        }
    }

    public static NaturalSpawner.SpawnState runRemainingTasks(
            ServerLevel level, int spawnableChunkCount, Iterable<Entity> entities, NaturalSpawner.ChunkGetter chunkGetter, LocalMobCapCalculator calculator, final boolean countMobs
    ) {
        Object2IntOpenHashMap<MobCategory> map = mobsMap.get(level);
        if (map == null) return null; // skip if no data
        PotentialCalculator potentialCalculator = new PotentialCalculator();
        for (Entity entity : entities) {
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
        return new NaturalSpawner.SpawnState(spawnableChunkCount, map, potentialCalculator, calculator);
    }
}
