package fun.bm.lophine.config.modules.experiment;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.EXPERIMENT, name = "command")
public class CommandConfig implements IConfigModule {
    @ConfigInfo(name = "tick_command_enabled", comments =
            """
                    Allow to use tick command""")
    public static boolean tick = false;

    @ConfigInfo(name = "trigger_command_enabled", comments =
            """
                    Allow to use trigger command""")
    public static boolean trigger = false;

    @ConfigInfo(name = "function_command_enabled", comments =
            """
                    Allow to use function command""")
    public static boolean function = false;

    @ConfigInfo(name = "scoreboard_command_enabled", comments =
            """
                    Allow to use scoreboard command""")
    public static boolean scoreboard = false;

    @ConfigInfo(name = "enabled", directory = {"save_all_command"}, comments =
            """
                    Allow to use save-all command""")
    public static boolean saveAll = false;

    @ConfigInfo(name = "log_all_process", directory = {"save_all_command"}, comments =
            """
                    Log all process of save-all command to console""")
    public static boolean logAllProcess = false;

    @ConfigInfo(name = "save_all_command_timeout", directory = {"save_all_command"}, comments = """
            Maximum seconds to save before the chunk report it is timeout.""")
    public static long saveAllTimeout = 30;
}