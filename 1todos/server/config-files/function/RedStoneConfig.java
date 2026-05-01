package fun.bm.lophine.config.modules.function;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "redstone")
public class RedStoneConfig implements IConfigModule {
    @TransformedConfig(name = "shears_rotate", directory = {"misc", "redstone"})
    @TransformedConfig(name = "allow_skip_cooldown", directory = {"misc", "redstone"})
    @ConfigInfo(name = "shears_rotate", comments =
            """
                    Allows you to use the Shears to right-click to rotate the block.""")
    public static boolean shears = false;
}