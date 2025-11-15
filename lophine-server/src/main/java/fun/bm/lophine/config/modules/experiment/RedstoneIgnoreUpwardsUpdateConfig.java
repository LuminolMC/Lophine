package fun.bm.lophine.config.modules.experiment;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.EXPERIMENT, name = "redstone-ignore-upwards-update")
public class RedstoneIgnoreUpwardsUpdateConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Should the pre-1.20 mechanism be reintroduced: 
            Redstone dust does not connect to adjacent redstone dust on trapdoors that are open      
            Pre-1.20.2 mechanism: Redstone dust, redstone repeaters, and redstone comparators do not check for attachment when receiving status updates from below""")
    public static boolean enabled = false;

}
