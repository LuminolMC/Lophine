package fun.bm.lophine.config.modules.optimizations;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.HotReloadUnsupported;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.OPTIMIZATIONS, name = "waypoint")
public class WayPointOptimizedTableConfig implements IConfigModule {
    @HotReloadUnsupported
    @ConfigInfo(name = "optimizedTable", comments = """
            Should use optimized table instead of normal concurrent table for waypoints.
            May improve performance when there are many waypoints and players.
            When enabled, more memory is needed to store data.""")
    public static boolean optimizedTable = false;
}
