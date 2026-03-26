package fun.bm.lophine.config.carpet.modules;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import fun.bm.lophine.config.carpet.CarpetCompatSync;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@ConfigClassInfo(
        category = EnumConfigCategory.ROOT,
        name = "core",
        directory = {"carpet"}
)
public class CoreConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Enable carpet features.
            If you want to use any function from Carpet modifier,
            you need to enable it.
            WARNING: IF YOU ENABLED IT, ORIGINAL CONFIG IN LOPHINE CONFIG WILL BE OVERWRITTEN.""")
    public static boolean enabled = false;

    @Override
    public void onLoaded(CommentedFileConfig configInstance, @Nullable Set<Exception> e) {
        CarpetCompatSync.apply();
    }
}
