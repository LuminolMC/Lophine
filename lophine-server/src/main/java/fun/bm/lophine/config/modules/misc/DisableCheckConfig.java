package fun.bm.lophine.config.modules.misc;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;

public class DisableCheckConfig implements IConfigModule {
    @ConfigInfo(baseName = "disable-op-move-check", comments = """
            Disable the check for the operator's move check""")
    public static boolean disableOpMoveCheck = false;

    @ConfigInfo(baseName = "disable-op-fly-check", comments = """
            Disable the check for the operator's fly check""")
    public static boolean disableOpFlyCheck = false;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.MISC;
    }

    @Override
    public String getBaseName() {
        return "disable-check";
    }
}
