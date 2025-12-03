package fun.bm.lophine.config.modules.experiment;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.HotReloadUnsupported;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(name = "global_entities_counter", category = EnumConfigCategory.EXPERIMENT)
public class GlobalEntitiesCounter implements IConfigModule {
    @HotReloadUnsupported
    @ConfigInfo(name = "enabled", comments = """
            Enable global entities counter.
            You need to set per-player-mob-spawns to false on paper-world-defaults.yml or paper-world.yml""")
    public static boolean enabled = false;

    @HotReloadUnsupported
    @ConfigInfo(name = "async", comments = "Enable Asynchronous(maybe cause bugs)")
    public static boolean async = false;
}
