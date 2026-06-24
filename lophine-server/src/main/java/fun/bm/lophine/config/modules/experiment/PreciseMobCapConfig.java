package fun.bm.lophine.config.modules.experiment;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.HotReloadUnsupported;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(name = "precise_mob_cap", category = EnumConfigCategory.EXPERIMENT)
public class PreciseMobCapConfig implements IConfigModule {
    @HotReloadUnsupported
    @ConfigInfo(name = "enabled", comments = """
            Enable precise mob cap calculation with incremental counting.
            Replaces the periodic full-scan with event-driven real-time updates.
            This supersedes global_entities_counter when both are enabled.
            You need to set per-player-mob-spawns to false on paper-world-defaults.yml or paper-world.yml""")
    public static boolean enabled = false;

    @ConfigInfo(name = "count-all-mobs", comments = """
            When true, count all mobs for mob cap (vanilla behavior).
            When false, only count naturally spawned and chunk-gen mobs (Paper optimization).
            Overrides paper's countAllMobsForSpawning when this feature is enabled.""")
    public static boolean countAllMobs = true;
}
