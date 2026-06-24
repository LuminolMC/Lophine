package fun.bm.lophine.utils;

import ca.spottedleaf.moonrise.common.misc.PositionCountingAreaMap;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fun.bm.lophine.config.modules.experiment.PreciseMobCapConfig;
import fun.bm.lophine.config.modules.fixes.VanillaLikeExperienceConfig;
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

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static net.minecraft.world.level.NaturalSpawner.getRoughBiome;

public class PreciseMobCapCounter {
    private static final Map<ServerLevel, EnumMap<MobCategory, AtomicInteger>> globalMobCounts = new ConcurrentHashMap<>();
    private static final Map<ServerLevel, Cache<Integer, PositionCountingAreaMap<ServerPlayer>>> areaMaps = new ConcurrentHashMap<>();
    private static final Map<ServerLevel, VolatileIntHolder> spawnableChunkCountCache = new ConcurrentHashMap<>();
    private static final Set<Integer> uniqueIds = ConcurrentHashMap.newKeySet();
    private static int lastUsedId = 0;

    private static final class VolatileIntHolder {
        volatile int value = 0;
    }

    public static int generateUniqueId() {
        synchronized (uniqueIds) {
            int id = lastUsedId;
            while (uniqueIds.contains(id)) {
                id++;
            }
            lastUsedId = id;
            uniqueIds.add(id);
            return id;
        }
    }

    public static void releaseUniqueId(int id) {
        uniqueIds.remove(id);
    }

    public static boolean shouldCount(Entity entity) {
        MobCategory category = entity.getType().getCategory();
        if (category == MobCategory.MISC) return false;

        if (entity instanceof Mob mob && (mob.isPersistenceRequired() || mob.requiresCustomPersistence())) {
            return false;
        }

        if (VanillaLikeExperienceConfig.enabled || PreciseMobCapConfig.countAllMobs) {
            return true;
        }

        return entity.spawnReason == CreatureSpawnEvent.SpawnReason.NATURAL
                || entity.spawnReason == CreatureSpawnEvent.SpawnReason.CHUNK_GEN;
    }

    public static void onEntityTrackingStart(ServerLevel level, Entity entity) {
        if (!shouldCount(entity)) return;
        MobCategory category = entity.getType().getCategory();
        getOrCreateCounts(level).computeIfAbsent(category, k -> new AtomicInteger(0)).incrementAndGet();
    }

    public static void onEntityTrackingEnd(ServerLevel level, Entity entity) {
        if (!shouldCount(entity)) return;
        MobCategory category = entity.getType().getCategory();
        EnumMap<MobCategory, AtomicInteger> counts = getOrCreateCounts(level);
        AtomicInteger counter = counts.get(category);
        if (counter != null) {
            counter.updateAndGet(v -> Math.max(0, v - 1));
        }
    }

    public static Object2IntOpenHashMap<MobCategory> getGlobalCounts(ServerLevel level) {
        EnumMap<MobCategory, AtomicInteger> counts = globalMobCounts.get(level);
        Object2IntOpenHashMap<MobCategory> result = new Object2IntOpenHashMap<>();
        if (counts != null) {
            for (Map.Entry<MobCategory, AtomicInteger> entry : counts.entrySet()) {
                result.put(entry.getKey(), entry.getValue().get());
            }
        }
        return result;
    }

    public static void registerAreaMap(ServerLevel level, PositionCountingAreaMap<ServerPlayer> map, int uniqueId) {
        Cache<Integer, PositionCountingAreaMap<ServerPlayer>> cache = areaMaps.computeIfAbsent(
                level, k -> CacheBuilder.newBuilder().concurrencyLevel(16).weakValues().build()
        );
        cache.put(uniqueId, map);
    }

    public static void unregisterAreaMap(ServerLevel level, int uniqueId) {
        Cache<Integer, PositionCountingAreaMap<ServerPlayer>> cache = areaMaps.get(level);
        if (cache != null) {
            cache.invalidate(uniqueId);
        }
    }

    public static void tickSpawnableChunks(ServerLevel level) {
        Cache<Integer, PositionCountingAreaMap<ServerPlayer>> cache = areaMaps.get(level);
        if (cache == null) return;

        int total = 0;
        for (PositionCountingAreaMap<ServerPlayer> map : cache.asMap().values()) {
            if (map != null) {
                total += map.getTotalPositions();
            }
        }

        spawnableChunkCountCache.computeIfAbsent(level, k -> new VolatileIntHolder()).value = total;
    }

    public static int getSpawnableChunkCount(ServerLevel level) {
        VolatileIntHolder holder = spawnableChunkCountCache.get(level);
        return holder != null ? holder.value : 0;
    }

    public static @Nullable NaturalSpawner.SpawnState createSpawnState(
            ServerLevel level,
            Iterable<Entity> localEntities,
            NaturalSpawner.ChunkGetter chunkGetter,
            @Nullable LocalMobCapCalculator localMobCapCalculator
    ) {
        int chunkCount = getSpawnableChunkCount(level);
        if (chunkCount == 0) return null;

        Object2IntOpenHashMap<MobCategory> globalCounts = getGlobalCounts(level);

        PotentialCalculator potentialCalculator = new PotentialCalculator();
        for (Entity entity : localEntities) {
            if (entity == null || entity.isRemoved() || !entity.isAlive()) continue;
            if (!shouldCount(entity)) continue;

            BlockPos pos = entity.blockPosition();
            chunkGetter.query(ChunkPos.pack(pos), chunk -> {
                MobSpawnSettings.MobSpawnCost mobSpawnCost = getRoughBiome(pos, chunk).getMobSettings().getMobSpawnCost(entity.getType());
                if (mobSpawnCost != null) {
                    potentialCalculator.addCharge(entity.blockPosition(), mobSpawnCost.charge());
                }

                if (localMobCapCalculator != null && entity instanceof Mob) {
                    localMobCapCalculator.addMob(chunk.getPos(), entity.getType().getCategory());
                }
            });
        }

        return new NaturalSpawner.SpawnState(chunkCount, globalCounts, potentialCalculator, localMobCapCalculator);
    }

    public static void onWorldUnload(ServerLevel level) {
        globalMobCounts.remove(level);
        areaMaps.remove(level);
        spawnableChunkCountCache.remove(level);
    }

    private static EnumMap<MobCategory, AtomicInteger> getOrCreateCounts(ServerLevel level) {
        return globalMobCounts.computeIfAbsent(level, k -> new EnumMap<>(MobCategory.class));
    }
}
