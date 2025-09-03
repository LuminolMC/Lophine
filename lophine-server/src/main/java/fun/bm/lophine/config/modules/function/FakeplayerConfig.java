package fun.bm.lophine.config.modules.function;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.command.bot.BotCommand;

import java.util.List;

@ConfigClassInfo(configAttribution = EnumConfigCategory.FUNCTION, mainName = "fakeplayer")
public class FakeplayerConfig implements IConfigModule {
    @ConfigInfo(baseName = "enable", comments = """
            Enable fakeplayer functionality""")
    public static boolean enable = true;

    @ConfigInfo(baseName = "unable-fakeplayer-names", comments = """
            List of names that cannot be used for fakeplayers""")
    public static List<String> unableNames = List.of("player-name");

    @ConfigInfo(baseName = "limit", comments = """
            Maximum number of fakeplayers allowed""")
    public static int limit = 10;

    @ConfigInfo(baseName = "prefix", comments = """
            Prefix for fakeplayer names""")
    public static String prefix = "";

    @ConfigInfo(baseName = "suffix", comments = """
            Suffix for fakeplayer names""")
    public static String suffix = "";

    @ConfigInfo(baseName = "regen-amount", comments = """
            Regeneration amount for fakeplayers""")
    public static double regenAmount = 0.0;

    @ConfigInfo(baseName = "resident-fakeplayer", comments = """
            Allow fakeplayers to be resident""")
    public static boolean canResident = false;

    @ConfigInfo(baseName = "open-fakeplayer-inventory", comments = """
            Allow opening fakeplayer inventory""")
    public static boolean canOpenInventory = false;

    @ConfigInfo(baseName = "use-action", comments = """
            Allow fakeplayers to use actions""")
    public static boolean canUseAction = true;

    @ConfigInfo(baseName = "modify-config", comments = """
            Allow modifying fakeplayer config""")
    public static boolean canModifyConfig = false;

    @ConfigInfo(baseName = "manual-save-and-load", comments = """
            Allow manual save and load of fakeplayers""")
    public static boolean canManualSaveAndLoad = false;

    @ConfigInfo(baseName = "cache-skin", comments = """
            Use skin cache for fakeplayers""")
    public static boolean useSkinCache = false;

    @ConfigInfo(baseName = "always-send-data", comments = """
            Always send data for fakeplayers""")
    public static boolean canSendDataAlways = true;

    @ConfigInfo(baseName = "skip-sleep-check", comments = """
            Skip sleep check for fakeplayers""")
    public static boolean canSkipSleep = false;

    @ConfigInfo(baseName = "spawn-phantom", comments = """
            Allow phantoms to spawn for fakeplayers""")
    public static boolean canSpawnPhantom = false;

    @ConfigInfo(baseName = "simulation-distance", comments = """
            Simulation distance for fakeplayers (-1 for default)""")
    public static int simulationDistance = -1;

    @ConfigInfo(baseName = "enable-locator-bar", comments = """
            Enable locator bar for fakeplayers""")
    public static boolean enableLocatorBar = false;

    public static ServerBot.TickType tickType = ServerBot.TickType.ENTITY_LIST;

    private BotCommand command = null;

    private boolean registered = false;

    public static int getSimulationDistance(ServerBot bot) {
        return simulationDistance == -1 ? bot.getBukkitEntity().getSimulationDistance() : simulationDistance;
    }

    @Override
    public void onLoaded(CommentedFileConfig configInstance) {
        if (enable) {
            command = new BotCommand();
            command.register();
            registered = true;
        }
    }

    @Override
    public void onUnloaded(CommentedFileConfig configInstance) {
        if (registered) {
            command.unregister();
            command = null;
        }
    }
}
