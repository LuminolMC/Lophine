package fun.bm.lophine.carpet.config.modules;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(
        category = EnumConfigCategory.ROOT,
        name = "hopper_counter",
        directory = {"carpet"},
        comments = """
                Hopper counter compatibility mapped onto Lophine's wool hopper counter implementation."""
)
public class CounterCompatConfig implements IConfigModule {
    @ConfigInfo(name = "hopperCounters", comments = """
            Enable the existing wool hopper counter implementation.""")
    public static boolean hopperCounters = false;

    @ConfigInfo(name = "hopperCountersUnlimitedSpeed", comments = """
            Remove the hopper transfer speed limit for counters.
            Only effective when hopperCounters is enabled.""")
    public static boolean hopperCountersUnlimitedSpeed = false;
}
