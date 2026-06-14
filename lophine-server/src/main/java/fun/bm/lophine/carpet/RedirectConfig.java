package fun.bm.lophine.carpet;

import fun.bm.lophine.carpet.config.modules.GeneralCompatConfig;
import me.earthme.luminol.config.ConfigManager;
import me.earthme.luminol.config.ConfigsInstance;

public class RedirectConfig {
    public static void redirect() {
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
}
