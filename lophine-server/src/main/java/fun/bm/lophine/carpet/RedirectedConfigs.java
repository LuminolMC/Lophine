package fun.bm.lophine.carpet;

import fun.bm.lophine.carpet.config.modules.GeneralCompatConfig;
import fun.bm.lophine.carpet.config.modules.WoolHopperCounterConfig;
import me.earthme.luminol.config.ConfigManager;
import me.earthme.luminol.config.ConfigsInstance;

public class RedirectedConfigs {
    public static void redirect() {
        updateSuppressionCrashFix();
        commandTick();
        optimizedDragonRespawn();
    }

    private static void updateSuppressionCrashFix() {
        try {
            ConfigsInstance config = ConfigManager.getConfigs("lophine");
            boolean updateSuppressionCrashFixEnabled = config.getConfigOrigin("fixes.update-suppression-crash-fix.enabled");
            if (updateSuppressionCrashFixEnabled) {
                GeneralCompatConfig.amsUpdateSuppressionCrashFix = true;
                GeneralCompatConfig.yeetUpdateSuppressionCrash = true;
            }
            config.removeConfig("enabled", new String[]{"fixes", "update-suppression-crash-fix"});
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
                config.removeConfig("tick_command_enabled", new String[]{"experiment", "command"});
            } catch (Exception ignored) {
            }
            // then check luminol
            try {
                ConfigsInstance config = ConfigManager.getConfigs("luminol");
                shouldEnabled = shouldEnabled || (boolean) config.getConfigOrigin("experiment.command.enable_tick_command");
                config.removeConfig("enable_tick_command", new String[]{"experiment", "command"});
            } catch (Exception ignored) {
            }

            if (shouldEnabled) {
                GeneralCompatConfig.commandTick = true;
            }
        } catch (Exception ignored) {
        }
    }

    private static void optimizedDragonRespawn() {
        try {
            ConfigsInstance config = ConfigManager.getConfigs("luminol");
            boolean optimizedDragonRespawnEnabled = config.getConfigOrigin("optimizations.end_dragon.optimized_dragon_respawn");
            if (optimizedDragonRespawnEnabled) {
                GeneralCompatConfig.optimizedDragonRespawn = true;
            }
            config.removeConfig("optimized_dragon_respawn", new String[]{"optimizations", "end_dragon"});
        } catch (Exception ignored) {
        }
    }

    private static void woolHopperCounter() {
        try {
            ConfigsInstance config = ConfigManager.getConfigs("lophine");
            boolean woolHopperCounterEnabled = config.getConfigOrigin("function.wool-hopper-counter.enabled");
            boolean woolHopperCounterUnlimitedSpeed = config.getConfigOrigin("function.wool-hopper-counter.unlimited-speed");
            if (woolHopperCounterEnabled) {
                WoolHopperCounterConfig.hopperCounters = true;
            }
            config.removeConfig("enabled", new String[]{"function", "wool-hopper-counter"});
            if (woolHopperCounterUnlimitedSpeed) {
                WoolHopperCounterConfig.hopperCountersUnlimitedSpeed = true;
            }
            config.removeConfig("unlimited-speed", new String[]{"function", "wool-hopper-counter"});
        } catch (Exception ignored) {
        }
    }
}
