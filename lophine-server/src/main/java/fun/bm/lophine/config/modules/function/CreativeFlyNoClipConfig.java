package fun.bm.lophine.config.modules.function;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.protocol.CarpetServerProtocol;

import java.util.Set;

@ConfigClassInfo(name = "creative_fly_no_clip", category = EnumConfigCategory.FUNCTION)
public class CreativeFlyNoClipConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Whether to enable creative fly no clip.
            When enabled, players in creative mode will not collide with blocks while flying.
            This allows them to pass through blocks without obstruction.""")
    public static boolean enabled = false;

    @Override
    public void onLoaded(CommentedFileConfig configInstance, @Nullable Set<Exception> e) {
        CarpetServerProtocol.CarpetRules.register(CarpetServerProtocol.CarpetRule.of("carpet", "creativeNoClip", enabled));
    }
}
