package fun.bm.lophine.carpet.config.modules;

//import fun.bm.lophine.carpet.CarpetCompatSync;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(
        category = EnumConfigCategory.ROOT,
        name = "core",
        directory = {"carpet"}
)
public class CoreConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Enable carpet features.
            If you want to use some function from Carpet modifier,
            you need to enable it.
            (some function is not managed by this option)
            
            ONLY GENERAL DIRECTORY WAS CONTROLLED BY THIS OPTION.
            WARNING: IF YOU ENABLED IT, ORIGINAL CONFIG IN LOPHINE CONFIG WILL BE OVERWRITTEN.""")
    public static boolean enabled = false;

    @Override
    public void beforeFinalLoad() {
//        CarpetCompatSync.apply();
    }
}
