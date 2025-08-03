package fun.bm.lophine.config.modules.misc;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;

import java.util.List;

public class SurvuxProtocolConfig implements IConfigModule {
    @ConfigInfo(baseName = "entity-protocol")
    public static boolean entityProtocol = false;

    @ConfigInfo(baseName = "hud-logger-protocol")
    public static boolean hudLoggerProtocol = false;

    @ConfigInfo(baseName = "hud-metadata-protocol")
    public static boolean hudMetadataProtocol = false;

    @ConfigInfo(baseName = "hud-metadata-share-seed")
    public static boolean hudMetadataShareSeed = false;

    @ConfigInfo(baseName = "structure-protocol")
    public static boolean structureProtocol = false;

    @ConfigInfo(baseName = "hud-enabled-loggers")
    public static List<String> hudEnabledLoggers = List.of("tps", "mob_caps");

    @ConfigInfo(baseName = "hud-update-interval")
    public static int hudUpdateInterval = 1;

    @ConfigInfo(baseName = "litematics-enabled")
    public static boolean litematicsEnabled = false;

    @ConfigInfo(baseName = "litematics-max-nbt-size")
    public static int litematicsMaxNbtSize = 2097152;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.MISC;
    }

    @Override
    public String getBaseName() {
        return "servux-protocol";
    }
}
