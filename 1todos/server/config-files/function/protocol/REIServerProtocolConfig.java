package fun.bm.lophine.config.modules.function.protocol;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "rei", directory = {"protocol"})
public class REIServerProtocolConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Enable Roughly Enough Items protocol support""")
    public static boolean enabled = false;
}
