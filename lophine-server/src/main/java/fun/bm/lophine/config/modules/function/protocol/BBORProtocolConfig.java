package fun.bm.lophine.config.modules.function.protocol;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "bbor", directory = {"protocol"})
public class BBORProtocolConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Enable BBOR protocol support""")
    public static boolean enabled = false;
}
