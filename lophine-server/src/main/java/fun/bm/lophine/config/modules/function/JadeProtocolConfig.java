package fun.bm.lophine.config.modules.function;

import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "jade-protocol")
public class JadeProtocolConfig {
    @ConfigInfo(name = "enabled", comments = """
            Enable Jade protocol support""")
    public static boolean enabled = false;
}
