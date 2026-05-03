package fun.bm.lophine.config.modules.misc;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.MISC, name = "item-entity")
public class ItemEntityConfig implements IConfigModule {
    @ConfigInfo(name = "follow-tick-sequence-merge", comments = """
            Due to Paper's modification of the merge radius,
            when the merge radius is large and stacks containing many items get stuck in an unexpected position,
            individual items may never reach their destination.
            This configuration option is added to fix this behavior.""")
    public static boolean followTickSequenceMerge = false;
}
