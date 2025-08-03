package fun.bm.lophine.config.modules.removed;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;

public class RemovedConfig implements IConfigModule {
    @TransformedConfig(name = "old_replaceable_by_mushrooms", category = {"misc", "old-feature"}, transform = false)
    @TransformedConfig(name = "old_nether_portal_collision", category = {"misc", "old-feature"}, transform = false)
    @TransformedConfig(name = "better_shulker_box", category = {"misc", "container_expansion"}, transform = false)
    @ConfigInfo(baseName = "removed", comments =
            """
                    RemovedConfig redirect to here, no any function.""")
    public static boolean enabled = true;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.REMOVED;
    }

    @Override
    public String getBaseName() {
        return "removed_config";
    }
}