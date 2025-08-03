package fun.bm.lophine.config.modules.optimizations;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;

public class NetworkConfig implements IConfigModule {
    @ConfigInfo(baseName = "unlimit-packet", comments = """
                    Disable packet limit""")
    public static boolean disablePacketLimit = false;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.OPTIMIZATIONS;
    }

    @Override
    public String getBaseName() {
        return "network";
    }
}