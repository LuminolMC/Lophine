package fun.bm.lophine.config.modules.function.protocol;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.CommandSuggestions;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;
import me.earthme.luminol.enums.EnumConfigCategory;

import java.util.List;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "servux", directory = {"protocol"})
public class ServuxProtocolConfig implements IConfigModule {
    @TransformedConfig(name = "entity-protocol", directory = {"function", "servux-protocol", "data"})
    @TransformedConfig(name = "entity-protocol", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "entity-protocol", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "entity-protocol", directory = {"data"})
    public static boolean entityProtocol = false;

    @TransformedConfig(name = "hud-logger-protocol", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "hud-logger-protocol", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "hud-logger-protocol", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "hud-logger-protocol")
    public static boolean hudLoggerProtocol = false;

    @TransformedConfig(name = "hud-metadata-protocol", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "hud-metadata-protocol", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "hud-metadata-protocol", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "hud-metadata-protocol")
    public static boolean hudMetadataProtocol = false;

    @TransformedConfig(name = "hud-metadata-share-seed", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "hud-metadata-share-seed", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "hud-metadata-share-seed", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "hud-metadata-share-seed")
    public static boolean hudMetadataShareSeed = false;

    @TransformedConfig(name = "structure-protocol", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "structure-protocol", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "structure-protocol", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "structure-protocol")
    public static boolean structureProtocol = false;

    @TransformedConfig(name = "hud-enabled-loggers", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "hud-enabled-loggers", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "hud-enabled-loggers", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "hud-enabled-loggers")
    public static List<String> hudEnabledLoggers = List.of("tps", "mob_caps");

    @TransformedConfig(name = "hud-update-interval", directory = {"function", "servux-protocol"})
    @TransformedConfig(name = "hud-update-interval", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "hud-update-interval", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "hud-update-interval")
    public static int hudUpdateInterval = 1;

    @TransformedConfig(name = "litematics-enabled", directory = {"function", "servux-protocol", "litematics"})
    @TransformedConfig(name = "litematics-enabled", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "litematics-enabled", directory = {"misc", "survux-protocol"})
    @ConfigInfo(name = "litematics-enabled", directory = {"litematics"})
    public static boolean litematicsEnabled = false;

    @TransformedConfig(name = "litematics-max-nbt-size", directory = {"function", "servux-protocol", "litematics"})
    @TransformedConfig(name = "litematics-max-nbt-size", directory = {"function", "survux-protocol"})
    @TransformedConfig(name = "litematics-max-nbt-size", directory = {"misc", "survux-protocol"})
    @CommandSuggestions(suggest = {"-1", "2097152"})
    @ConfigInfo(name = "litematics-max-nbt-size", directory = {"litematics"})
    public static int litematicsMaxNbtSize = 2097152;

    @TransformedConfig(name = "litematics-print-max-delay-ticks", directory = {"function", "servux-protocol", "litematics"})
    @TransformedConfig(name = "litematics-print-max-delay-ticks", directory = {"function", "survux-protocol"})
    @CommandSuggestions(suggest = {"-1", "1200"})
    @ConfigInfo(name = "litematics-print-max-delay-ticks", directory = {"litematics"}, comments = "The max delay ticks for printing litematics, -1 to disable")
    public static int maxDelay = 1200;
}
