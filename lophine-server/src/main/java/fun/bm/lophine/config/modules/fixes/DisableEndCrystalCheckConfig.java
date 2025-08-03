package fun.bm.lophine.config.modules.fixes;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;

public class DisableEndCrystalCheckConfig implements IConfigModule {
    @TransformedConfig(name = "disable_end_crystal_check", category = {"misc", "end_crystal"})
    @ConfigInfo(baseName = "disable_end_crystal_check", comments =
            """
                    Disable paper's End Crystal position check.
                    It reverts to vanilla respawn dragon logic.""")
    public static boolean disableCheck = false;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.FIXES;
    }

    @Override
    public String getBaseName() {
        return "end_crystal";
    }
}