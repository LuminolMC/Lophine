package fun.bm.lophine.carpet;

import fun.bm.lophine.carpet.config.modules.CoreConfig;
import fun.bm.lophine.carpet.config.modules.CounterCompatConfig;
import fun.bm.lophine.carpet.config.modules.FakePlayerCompatConfig;
import fun.bm.lophine.carpet.config.modules.GeneralCompatConfig;
import fun.bm.lophine.config.modules.experiment.RedStoneConfig;
import fun.bm.lophine.config.modules.fixes.UpdateSuppressionCrashFixConfig;
import fun.bm.lophine.config.modules.function.CreativeFlyNoClipConfig;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import fun.bm.lophine.config.modules.function.LanguageConfig;
import fun.bm.lophine.config.modules.function.WoolHopperCounterConfig;
import fun.bm.lophine.protocol.CarpetLoggerProtocol;
import me.earthme.luminol.config.modules.experiment.CommandConfig;
import me.earthme.luminol.config.modules.optimizations.OptimizedDragonRespawnConfig;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.protocol.CarpetServerProtocol;

import java.util.List;

// WARNING: THIS FILE NEED TO FULLY REWRITTEN
public final class CarpetCompatSync {
    private static boolean init = false;

    public static void apply() {
        if (init) return;
        if (CoreConfig.enabled) {
            applyGeneralRules();
            applyFakePlayerRules();
            applyCounterRules();
        }
        CarpetLoggerProtocol.refreshConfiguredDefaults(!init);
        registerProtocolRules();
        init = true;
    }

    private static void applyGeneralRules() {
        LanguageConfig.lang = GeneralCompatConfig.language;
        UpdateSuppressionCrashFixConfig.enabled = GeneralCompatConfig.amsUpdateSuppressionCrashFix || GeneralCompatConfig.yeetUpdateSuppressionCrash;
        RedStoneConfig.redstoneIgnoreUpwardsUpdate = GeneralCompatConfig.dustTrapdoorReintroduced;
        RedStoneConfig.cce = GeneralCompatConfig.shulkerBoxCCEReintroduced;
        RedStoneConfig.instantBlockUpdater = GeneralCompatConfig.instantBlockUpdaterReintroduced;
        CommandConfig.tick = GeneralCompatConfig.commandTick;
        CreativeFlyNoClipConfig.enabled = GeneralCompatConfig.creativeNoClip;
        OptimizedDragonRespawnConfig.optimizedRespawn = GeneralCompatConfig.optimizedDragonRespawn;
    }

    private static void applyFakePlayerRules() {
        FakeplayerConfig.enable = FakePlayerCompatConfig.commandBot || FakePlayerCompatConfig.commandPlayer;
        FakeplayerConfig.canResident = FakePlayerCompatConfig.fakePlayerResident;
        FakeplayerConfig.canOpenInventory = FakePlayerCompatConfig.openFakePlayerInventory;
        FakeplayerConfig.tickType = FakePlayerCompatConfig.fakePlayerTicksLikeRealPlayer
                ? ServerBot.TickType.NETWORK
                : ServerBot.TickType.ENTITY_LIST;
    }

    private static void applyCounterRules() {
        WoolHopperCounterConfig.enabled = CounterCompatConfig.hopperCounters;
        WoolHopperCounterConfig.unlimitedSpeed = CounterCompatConfig.hopperCountersUnlimitedSpeed;
    }

    private static List<String> sanitizeDefaultLoggers(List<String> configuredLoggers) {
        return configuredLoggers == null ? List.of() : List.copyOf(configuredLoggers);
    }

    private static void registerProtocolRules() {
        CarpetServerProtocol.CarpetRules.clear();

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "language", GeneralCompatConfig.language));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "amsUpdateSuppressionCrashFix", GeneralCompatConfig.amsUpdateSuppressionCrashFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "yeetUpdateSuppressionCrash", GeneralCompatConfig.yeetUpdateSuppressionCrash));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "dustTrapdoorReintroduced", GeneralCompatConfig.dustTrapdoorReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "shulkerBoxCCEReintroduced", GeneralCompatConfig.shulkerBoxCCEReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "instantBlockUpdaterReintroduced", GeneralCompatConfig.instantBlockUpdaterReintroduced));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "commandTick", GeneralCompatConfig.commandTick));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "creativeNoClip", GeneralCompatConfig.creativeNoClip));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedDragonRespawn", GeneralCompatConfig.optimizedDragonRespawn));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "antiSpamDisabled", GeneralCompatConfig.antiSpamDisabled));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "blockPlacementIgnoreEntity", GeneralCompatConfig.blockPlacementIgnoreEntity));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "creativeOpenContainerForcibly", GeneralCompatConfig.creativeOpenContainerForcibly));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "creativeOneHitKill", GeneralCompatConfig.creativeOneHitKill));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "observerNoDetection", GeneralCompatConfig.observerNoDetection));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "bambooModelNoOffset", GeneralCompatConfig.bambooModelNoOffset));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "creativeNoItemCooldown", GeneralCompatConfig.creativeNoItemCooldown));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "ctrlQCraftingFix", GeneralCompatConfig.ctrlQCraftingFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "carpetAlwaysSetDefault", GeneralCompatConfig.carpetAlwaysSetDefault));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "placementRotationFix", GeneralCompatConfig.placementRotationFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "tntDoNotUpdate", GeneralCompatConfig.tntDoNotUpdate));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "totallyNoBlockUpdate", GeneralCompatConfig.totallyNoBlockUpdate));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tiscmNetworkProtocol", GeneralCompatConfig.tiscmNetworkProtocol));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetorgaddition", "hopperNoItemCost", GeneralCompatConfig.hopperNoItemCost));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "explosionNoBlockDamage", GeneralCompatConfig.explosionNoBlockDamage));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedTNTHighPriority", GeneralCompatConfig.optimizedTNTHighPriority));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "tntPrimerMomentumRemoved", GeneralCompatConfig.tntPrimerMomentumRemoved));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntIgnoreRedstoneSignal", GeneralCompatConfig.tntIgnoreRedstoneSignal));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntDupingFix", GeneralCompatConfig.tntDupingFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "interactionUpdates", GeneralCompatConfig.interactionUpdates));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "xpNoCooldown", GeneralCompatConfig.xpNoCooldown));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "powerfulExpMending", GeneralCompatConfig.powerfulExpMending));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "clientSettingsLostOnRespawnFix", GeneralCompatConfig.clientSettingsLostOnRespawnFix));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "sensibleEnderman", GeneralCompatConfig.sensibleEnderman));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "entityInstantDeathRemoval", GeneralCompatConfig.entityInstantDeathRemoval));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "farmlandTrampledDisabled", GeneralCompatConfig.farmlandTrampledDisabled));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "shulkerGolem", GeneralCompatConfig.shulkerGolem));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "preventEndSpikeRespawn", GeneralCompatConfig.preventEndSpikeRespawn));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "yeetOutOfOrderChatKick", GeneralCompatConfig.yeetOutOfOrderChatKick));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "betterCraftableBoneBlock", GeneralCompatConfig.betterCraftableBoneBlock));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "betterCraftableDispenser", GeneralCompatConfig.betterCraftableDispenser));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "viewDistance", GeneralCompatConfig.viewDistance));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tickCommandPermission", GeneralCompatConfig.normalizedTickCommandPermission()));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tickFreezeCommandToggleable", GeneralCompatConfig.tickFreezeCommandToggleable));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "syncServerMsptMetricsData", GeneralCompatConfig.syncServerMsptMetricsData));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetorgaddition", "simpleInGameCalculator", GeneralCompatConfig.simpleInGameCalculator));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "microTiming", GeneralCompatConfig.microTiming));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "fastRedstoneDust", GeneralCompatConfig.fastRedstoneDust));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "lagFreeSpawning", GeneralCompatConfig.lagFreeSpawning));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedFastEntityMovement", GeneralCompatConfig.optimizedFastEntityMovement));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "optimizedHardHitBoxEntityCollision", GeneralCompatConfig.optimizedHardHitBoxEntityCollision));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "tntFuseDuration", GeneralCompatConfig.normalizedTntFuseDuration()));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "defaultLoggers", CarpetLoggerProtocol.serializeConfiguredDefaults(sanitizeDefaultLoggers(GeneralCompatConfig.defaultLoggers))));

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "commandBot", FakePlayerCompatConfig.commandBot));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "commandPlayer", FakePlayerCompatConfig.commandPlayer));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerResident", FakePlayerCompatConfig.fakePlayerResident));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "openFakePlayerInventory", FakePlayerCompatConfig.openFakePlayerInventory));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "fakePlayerTicksLikeRealPlayer", FakePlayerCompatConfig.fakePlayerTicksLikeRealPlayer));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerDefaultSurvivalMode", FakePlayerCompatConfig.fakePlayerDefaultSurvivalMode));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerInteractLikeClient", FakePlayerCompatConfig.fakePlayerInteractLikeClient));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoReplaceTool", FakePlayerCompatConfig.fakePlayerAutoReplaceTool));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoReplenishment", FakePlayerCompatConfig.fakePlayerAutoReplenishment));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpetamsaddition", "fakePlayerAutoReplenishmentFormShulkerBox", FakePlayerCompatConfig.fakePlayerAutoReplenishmentFormShulkerBox));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerAutoFish", FakePlayerCompatConfig.fakePlayerAutoFish));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("lophine", "fakePlayerReloadAction", FakePlayerCompatConfig.fakePlayerReloadAction));

        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "hopperCounters", CounterCompatConfig.hopperCounters));
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpettisaddition", "hopperCountersUnlimitedSpeed", CounterCompatConfig.hopperCountersUnlimitedSpeed));
    }
}
