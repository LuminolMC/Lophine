package fun.bm.lophine.config.modules.experiment;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;
import me.earthme.luminol.enums.EnumConfigCategory;
/*
* This is a config module for redstone in experimental level
* If we think configs from here is stable for future, we will move them to function module directory
*/
@ConfigClassInfo(category = EnumConfigCategory.EXPERIMENT, name = "redstone")
public class RedStoneConfig implements IConfigModule {
    @TransformedConfig(name = "enabled", directory = {"experiment", "redstone-ignore-upwards-update"})
    @ConfigInfo(name = "redstone-ignore-upwards-update", comments = """
            Should the pre-1.20 mechanism be reintroduced: 
            Redstone dust does not connect to adjacent redstone dust on trapdoors that are open      
            Pre-1.20.2 mechanism: Redstone dust, redstone repeaters, and redstone comparators do not check for attachment when receiving status updates from below""")
    public static boolean redstoneIgnoreUpwardsUpdate = false;

    @TransformedConfig(name = "enabled", directory = {"experiment", "cce-update-suppression"})
    @ConfigInfo(name = "cce-update-suppression", comments = """
            Is it permissible to use ClassCastException for update suppression?""")
    public static boolean cce = false;
}
