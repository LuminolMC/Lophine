package fun.bm.lophine.config.modules.misc;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

/*
 * This file is only for showing auto update config.
 * The function is implemented in luminol-server and not provide any function.
 * Please use luminol-server's auto update config.
 */
@ConfigClassInfo(
        category = EnumConfigCategory.MISC,
        name = "auto_update",
        comments = """
                Checks GitHub Releases for newer version's jars on a schedule.
                Downloads are staged under auto_update/lophine and written to auto_update/core.path,
                which Hyacinthusclip can consume on the next restart.
                
                ATTENTION: full config option should be edited in luminol config system >> misc >> auto_update"""
)
public class AutoUpdateConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Whether the server should check for updates automatically.
            You can enable it by edit this config, because they are both control the function.
            One of them enabled, the full function will be enabled.""")
    public static boolean enabled = false;
}
