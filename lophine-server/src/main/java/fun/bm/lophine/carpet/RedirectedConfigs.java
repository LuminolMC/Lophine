package fun.bm.lophine.carpet;

import fun.bm.lophine.carpet.config.modules.GeneralCompatConfig;
import fun.bm.lophine.carpet.config.modules.WoolHopperCounterConfig;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import me.earthme.luminol.config.ConfigManager;
import me.earthme.luminol.config.ConfigsInstance;

public class RedirectedConfigs {
    public static void redirect() {
        updateSuppressionCrashFix();
        commandTick();
        optimizedDragonRespawn();
        woolHopperCounter();
        fakeplayer();
        creativeFlyNoClip();
        ConfigManager.reApplyStagedConfigs();
        ConfigManager.saveConfigs();
    }

    private static void updateSuppressionCrashFix() {
        ConfigsInstance config = ConfigManager.getConfigs("lophine");
        try {
            boolean updateSuppressionCrashFixEnabled = config.getConfigOrigin("fixes.update-suppression-crash-fix.enabled");
            if (updateSuppressionCrashFixEnabled) {
                ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
                GeneralCompatConfig.amsUpdateSuppressionCrashFix = true;
                carpetConfig.setConfig(new String[]{"carpet", "general", "amsUpdateSuppressionCrashFix"}, true);
                GeneralCompatConfig.yeetUpdateSuppressionCrash = true;
                carpetConfig.setConfig(new String[]{"carpet", "general", "yeetUpdateSuppressionCrash"}, true);
            }
            config.removeConfig(new String[]{"fixes", "update-suppression-crash-fix", "enabled"});
        } catch (Exception ignored) {
        }
    }

    private static void commandTick() {
        try {
            boolean shouldEnabled = false;
            // first check lophine
            try {
                ConfigsInstance config = ConfigManager.getConfigs("lophine");
                shouldEnabled = config.getConfigOrigin("experiment.command.tick_command_enabled");
                config.removeConfig(new String[]{"experiment", "command", "tick_command_enabled"});
            } catch (Exception ignored) {
            }
            // then check luminol
            try {
                ConfigsInstance config = ConfigManager.getConfigs("luminol");
                shouldEnabled = shouldEnabled || (boolean) config.getConfigOrigin("experiment.command.enable_tick_command");
                config.removeConfig(new String[]{"experiment", "command", "enable_tick_command"});
            } catch (Exception ignored) {
            }

            if (shouldEnabled) {
                ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
                GeneralCompatConfig.commandTick = true;
                carpetConfig.setConfig(new String[]{"carpet", "general", "commandTick"}, true);
            }
        } catch (Exception ignored) {
        }
    }

    private static void optimizedDragonRespawn() {
        ConfigsInstance config = ConfigManager.getConfigs("luminol");
        try {
            boolean optimizedDragonRespawnEnabled = config.getConfigOrigin("optimizations.end_dragon.optimized_dragon_respawn");
            if (optimizedDragonRespawnEnabled) {
                ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
                GeneralCompatConfig.optimizedDragonRespawn = true;
                carpetConfig.setConfig(new String[]{"carpet", "general", "optimizedDragonRespawn"}, true);
            }
            config.removeConfig(new String[]{"optimizations", "end_dragon", "optimized_dragon_respawn"});
        } catch (Exception ignored) {
        }
    }

    private static void woolHopperCounter() {
        ConfigsInstance config = ConfigManager.getConfigs("lophine");
        try {
            boolean woolHopperCounterEnabled = config.getConfigOrigin("function.wool-hopper-counter.enabled");
            boolean woolHopperCounterUnlimitedSpeed = config.getConfigOrigin("function.wool-hopper-counter.unlimited-speed");
            ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
            if (woolHopperCounterEnabled) {
                WoolHopperCounterConfig.hopperCounters = true;
                carpetConfig.setConfig(new String[]{"carpet", "hopper_counter", "hopperCounters"}, true);
            }
            config.removeConfig(new String[]{"function", "wool-hopper-counter", "enabled"});
            if (woolHopperCounterUnlimitedSpeed) {
                WoolHopperCounterConfig.hopperCountersUnlimitedSpeed = true;
                carpetConfig.setConfig(new String[]{"carpet", "hopper_counter", "hopperCountersUnlimitedSpeed"}, true);
            }
            config.removeConfig(new String[]{"function", "wool-hopper-counter", "unlimited-speed"});
        } catch (Exception ignored) {
        }
    }

    private static void fakeplayer() {
        ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
        try {
            boolean bot = carpetConfig.getConfigOrigin("carpet.fakeplayer.commandBot");
            if (bot) {
                try {
                    ConfigsInstance lophineConfig = ConfigManager.getConfigs("lophine");
                    FakeplayerConfig.enable = true;
                    lophineConfig.setConfig(new String[]{"function", "fakeplayer", "enable"}, true);
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }
        carpetConfig.removeConfig(new String[]{"carpet", "fakeplayer", "commandBot"});
    }

    private static void creativeFlyNoClip() {
        ConfigsInstance config = ConfigManager.getConfigs("lophine");
        try {
            boolean creativeFlyNoClipEnabled = config.getConfigOrigin("function.creative_fly_no_clip.enabled");
            if (creativeFlyNoClipEnabled) {
                ConfigsInstance carpetConfig = ConfigManager.getConfigs("lophine_carpet");
                GeneralCompatConfig.creativeNoClip = true;
                carpetConfig.setConfig(new String[]{"carpet", "general", "creativeNoClip"}, true);
            }
            config.removeConfig(new String[]{"function", "creative_fly_no_clip", "enabled"});
        } catch (Exception ignored) {
        }
    }
}
