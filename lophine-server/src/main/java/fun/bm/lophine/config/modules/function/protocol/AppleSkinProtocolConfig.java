package fun.bm.lophine.config.modules.function.protocol;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "appleskin", directory = {"protocol"})
public class AppleSkinProtocolConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Enable AppleSkin protocol support""")
    public static boolean enabled = false;
    @ConfigInfo(name = "sync-tick-interval", comments = """
            Set AppleSkin Synchronization Frequency (Unit: Game Ticks)""")
    public static int syncTickInterval = 20;
}
