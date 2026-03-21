package fun.bm.lophine.config.carpet;

import fun.bm.lophine.config.carpet.modules.CarpetCounterCompatConfig;
import fun.bm.lophine.config.carpet.modules.CarpetFakePlayerCompatConfig;
import fun.bm.lophine.config.carpet.modules.CarpetGeneralCompatConfig;
import fun.bm.lophine.config.modules.experiment.CommandConfig;
import fun.bm.lophine.config.modules.function.CreativeFlyNoClipConfig;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import fun.bm.lophine.config.modules.function.LanguageConfig;
import fun.bm.lophine.config.modules.function.WoolHopperCounterConfig;
import fun.bm.lophine.config.modules.fixes.UpdateSuppressionCrashFixConfig;
import me.earthme.luminol.config.modules.optimizations.OptimizedDragonRespawnConfig;
import org.leavesmc.leaves.bot.ServerBot;
import fun.bm.lophine.protocol.CarpetLoggerProtocol;
import org.leavesmc.leaves.protocol.CarpetServerProtocol;

import java.util.List;

public final class CarpetCompatSync {
    private CarpetCompatSync() {
    }

    public static void apply() {
        applyGeneralRules();
        applyFakePlayerRules();
        applyCounterRules();
        registerProtocolRules();
    }

    private static void applyGeneralRules() {
        LanguageConfig.lang = CarpetGeneralCompatConfig.language;
        UpdateSuppressionCrashFixConfig.enabled = CarpetGeneralCompatConfig.amsUpdateSuppressionCrashFix || CarpetGeneralCompatConfig.yeetUpdateSuppressionCrash;
        fun.bm.lophine.config.modules.experiment.RedStoneConfig.redstoneIgnoreUpwardsUpdate = CarpetGeneralCompatConfig.dustTrapdoorReintroduced;
        fun.bm.lophine.config.modules.experiment.RedStoneConfig.cce = CarpetGeneralCompatConfig.shulkerBoxCCEReintroduced;
        fun.bm.lophine.config.modules.experiment.RedStoneConfig.instantBlockUpdater = CarpetGeneralCompatConfig.instantBlockUpdaterReintroduced;
        CommandConfig.tick = CarpetGeneralCompatConfig.commandTick;
        CreativeFlyNoClipConfig.enabled = CarpetGeneralCompatConfig.creativeNoClip;
        OptimizedDragonRespawnConfig.optimizedRespawn = CarpetGeneralCompatConfig.optimizedDragonRespawn;
        CarpetLoggerProtocol.refreshConfiguredDefaults();
    }

    private static void applyFakePlayerRules() {
        FakeplayerConfig.enable = CarpetFakePlayerCompatConfig.commandBot || CarpetFakePlayerCompatConfig.commandPlayer;
        FakeplayerConfig.canResident = CarpetFakePlayerCompatConfig.fakePlayerResident;
        FakeplayerConfig.canOpenInventory = CarpetFakePlayerCompatConfig.openFakePlayerInventory;
        FakeplayerConfig.tickType = CarpetFakePlayerCompatConfig.fakePlayerTicksLikeRealPlayer
                ? ServerBot.TickType.NETWORK
                : ServerBot.TickType.ENTITY_LIST;
    }

    private static void applyCounterRules() {
        WoolHopperCounterConfig.enabled = CarpetCounterCompatConfig.hopperCounters;
        WoolHopperCounterConfig.unlimitedSpeed = CarpetCounterCompatConfig.hopperCountersUnlimitedSpeed;
    }

    private static List<String> sanitizeDefaultLoggers(List<String> configuredLoggers) {
        return configuredLoggers == null ? List.of() : List.copyOf(configuredLoggers);
    }

    private static void registerProtocolRules() {
        CarpetServerProtocol.CarpetRules.clear();

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "language", CarpetGeneralCompatConfig.language));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "amsUpdateSuppressionCrashFix", CarpetGeneralCompatConfig.amsUpdateSuppressionCrashFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "yeetUpdateSuppressionCrash", CarpetGeneralCompatConfig.yeetUpdateSuppressionCrash));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "dustTrapdoorReintroduced", CarpetGeneralCompatConfig.dustTrapdoorReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "shulkerBoxCCEReintroduced", CarpetGeneralCompatConfig.shulkerBoxCCEReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "instantBlockUpdaterReintroduced", CarpetGeneralCompatConfig.instantBlockUpdaterReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "commandTick", CarpetGeneralCompatConfig.commandTick));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "creativeNoClip", CarpetGeneralCompatConfig.creativeNoClip));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedDragonRespawn", CarpetGeneralCompatConfig.optimizedDragonRespawn));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "antiSpamDisabled", CarpetGeneralCompatConfig.antiSpamDisabled));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "blockPlacementIgnoreEntity", CarpetGeneralCompatConfig.blockPlacementIgnoreEntity));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "creativeOpenContainerForcibly", CarpetGeneralCompatConfig.creativeOpenContainerForcibly));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "creativeOneHitKill", CarpetGeneralCompatConfig.creativeOneHitKill));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "observerNoDetection", CarpetGeneralCompatConfig.observerNoDetection));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "bambooModelNoOffset", CarpetGeneralCompatConfig.bambooModelNoOffset));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "creativeNoItemCooldown", CarpetGeneralCompatConfig.creativeNoItemCooldown));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "ctrlQCraftingFix", CarpetGeneralCompatConfig.ctrlQCraftingFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "carpetAlwaysSetDefault", CarpetGeneralCompatConfig.carpetAlwaysSetDefault));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "placementRotationFix", CarpetGeneralCompatConfig.placementRotationFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "tntDoNotUpdate", CarpetGeneralCompatConfig.tntDoNotUpdate));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "totallyNoBlockUpdate", CarpetGeneralCompatConfig.totallyNoBlockUpdate));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tiscmNetworkProtocol", CarpetGeneralCompatConfig.tiscmNetworkProtocol));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetorgaddition", "hopperNoItemCost", CarpetGeneralCompatConfig.hopperNoItemCost));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "explosionNoBlockDamage", CarpetGeneralCompatConfig.explosionNoBlockDamage));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedTNTHighPriority", CarpetGeneralCompatConfig.optimizedTNTHighPriority));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "tntPrimerMomentumRemoved", CarpetGeneralCompatConfig.tntPrimerMomentumRemoved));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntIgnoreRedstoneSignal", CarpetGeneralCompatConfig.tntIgnoreRedstoneSignal));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntDupingFix", CarpetGeneralCompatConfig.tntDupingFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "interactionUpdates", CarpetGeneralCompatConfig.interactionUpdates));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "xpNoCooldown", CarpetGeneralCompatConfig.xpNoCooldown));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "powerfulExpMending", CarpetGeneralCompatConfig.powerfulExpMending));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "clientSettingsLostOnRespawnFix", CarpetGeneralCompatConfig.clientSettingsLostOnRespawnFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "sensibleEnderman", CarpetGeneralCompatConfig.sensibleEnderman));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "entityInstantDeathRemoval", CarpetGeneralCompatConfig.entityInstantDeathRemoval));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "farmlandTrampledDisabled", CarpetGeneralCompatConfig.farmlandTrampledDisabled));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "shulkerGolem", CarpetGeneralCompatConfig.shulkerGolem));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "preventEndSpikeRespawn", CarpetGeneralCompatConfig.preventEndSpikeRespawn));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "yeetOutOfOrderChatKick", CarpetGeneralCompatConfig.yeetOutOfOrderChatKick));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "betterCraftableBoneBlock", CarpetGeneralCompatConfig.betterCraftableBoneBlock));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "betterCraftableDispenser", CarpetGeneralCompatConfig.betterCraftableDispenser));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "viewDistance", CarpetGeneralCompatConfig.viewDistance));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tickCommandPermission", CarpetGeneralCompatConfig.normalizedTickCommandPermission()));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tickFreezeCommandToggleable", CarpetGeneralCompatConfig.tickFreezeCommandToggleable));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "syncServerMsptMetricsData", CarpetGeneralCompatConfig.syncServerMsptMetricsData));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetorgaddition", "simpleInGameCalculator", CarpetGeneralCompatConfig.simpleInGameCalculator));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "microTiming", CarpetGeneralCompatConfig.microTiming));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "fastRedstoneDust", CarpetGeneralCompatConfig.fastRedstoneDust));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "lagFreeSpawning", CarpetGeneralCompatConfig.lagFreeSpawning));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedFastEntityMovement", CarpetGeneralCompatConfig.optimizedFastEntityMovement));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedHardHitBoxEntityCollision", CarpetGeneralCompatConfig.optimizedHardHitBoxEntityCollision));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntFuseDuration", CarpetGeneralCompatConfig.normalizedTntFuseDuration()));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "defaultLoggers", CarpetLoggerProtocol.serializeConfiguredDefaults(sanitizeDefaultLoggers(CarpetGeneralCompatConfig.defaultLoggers))));

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "commandBot", CarpetFakePlayerCompatConfig.commandBot));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "commandPlayer", CarpetFakePlayerCompatConfig.commandPlayer));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerResident", CarpetFakePlayerCompatConfig.fakePlayerResident));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "openFakePlayerInventory", CarpetFakePlayerCompatConfig.openFakePlayerInventory));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "fakePlayerTicksLikeRealPlayer", CarpetFakePlayerCompatConfig.fakePlayerTicksLikeRealPlayer));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerDefaultSurvivalMode", CarpetFakePlayerCompatConfig.fakePlayerDefaultSurvivalMode));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerInteractLikeClient", CarpetFakePlayerCompatConfig.fakePlayerInteractLikeClient));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoReplaceTool", CarpetFakePlayerCompatConfig.fakePlayerAutoReplaceTool));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoReplenishment", CarpetFakePlayerCompatConfig.fakePlayerAutoReplenishment));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerAutoReplenishmentFormShulkerBox", CarpetFakePlayerCompatConfig.fakePlayerAutoReplenishmentFormShulkerBox));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoFish", CarpetFakePlayerCompatConfig.fakePlayerAutoFish));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerReloadAction", CarpetFakePlayerCompatConfig.fakePlayerReloadAction));

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "hopperCounters", CarpetCounterCompatConfig.hopperCounters));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "hopperCountersUnlimitedSpeed", CarpetCounterCompatConfig.hopperCountersUnlimitedSpeed));
    }
}
