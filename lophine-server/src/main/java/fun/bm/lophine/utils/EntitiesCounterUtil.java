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

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.level.NaturalSpawner.getRoughBiome;

public class EntitiesCounterUtil {
    private static final WeakHashMap<ServerLevel, WeakCollection<ReferenceList<Entity>>> globalLoadedEntities = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, Object2IntOpenHashMap<MobCategory>> mobsMap = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, WeakHashMap<Thread, ChunkCounter>> fullSpawnableChunkCount = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, Integer> spawnableChunkCount = new WeakHashMap<>();
    private static final WeakHashMap<ServerLevel, CompletableFuture<Void>> tasks = new WeakHashMap<>();

    public static void addDataToLoaded(ReferenceList<Entity> data, ServerLevel level) {
        WeakCollection<ReferenceList<Entity>> data0 = globalLoadedEntities.computeIfAbsent(level, k -> new WeakCollection<>());
        data0.add(data);
    }

    public static void reportChunkCount(int count, ServerLevel level) {
        Map<Thread, ChunkCounter> counter = fullSpawnableChunkCount.computeIfAbsent(level, k -> new WeakHashMap<>());
        counter.put(Thread.currentThread(), new ChunkCounter(count));
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
                        // Paper end - Only count natural spawns
                        map.addTo(category, 1);
                    }
                    // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
                }
            }
            mobsMap.put(level, map);

            synchronized (fullSpawnableChunkCount) {
                for (ServerLevel world : fullSpawnableChunkCount.keySet()) {
                    int count = 0;
                    Map<Thread, ChunkCounter> map0 = fullSpawnableChunkCount.get(level);
                    if (map0 == null) continue;
                    Map<Thread, ChunkCounter> map1 = Map.copyOf(map0);
                    for (Thread thread : map1.keySet()) {
                        ChunkCounter counter = map1.get(thread);
                        counter.tick();
                        if (counter.timeout()) {
                            fullSpawnableChunkCount.get(world).remove(thread);
                        } else {
                            count += counter.getChunkCount();
                        }
                    }
                    spawnableChunkCount.put(level, count);
                }
            }
        };
        if (GlobalEntitiesCounter.async) {
            tasks.put(level, CompletableFuture.runAsync(task));
        } else {
            task.run();
        }
    }

    public static NaturalSpawner.SpawnState runRemainingTasks(
            ServerLevel level, int newSpawnableChunkCount, Iterable<Entity> entities, NaturalSpawner.ChunkGetter chunkGetter, LocalMobCapCalculator calculator, final boolean countMobs
    ) {
        reportChunkCount(newSpawnableChunkCount, level);
        Object2IntOpenHashMap<MobCategory> map = getMobsMap(level);
        if (map == null) return null; // skip if no data
        // Lophine start - Copy from net/minecraft/world/level/NaturalSpawner
        PotentialCalculator potentialCalculator = new PotentialCalculator();
        for (Entity entity : entities) {
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

    public static class ChunkCounter {
        private final int chunkCount;
        private int timeout = 20;

        public void tick() {
            timeout--;
        }

        public ChunkCounter(int chunkCount) {
            this.chunkCount = chunkCount;
        }

        public boolean timeout() {
            return timeout <= 0;
        }

        public int getChunkCount() {
            return chunkCount;
        }
    }
}
