package fun.bm.lophine.config.modules.function;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import fun.bm.lophine.command.counter.CounterCommand;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "wool-hopper-counter")
public class WoolHopperCounterConfig implements IConfigModule {
    @ConfigInfo(name = "enabled")
    public static boolean enabled = false;

    @ConfigInfo(name = "unlimited-speed")
    public static boolean unlimitedSpeed = false;

    @Override
    public void onLoaded(CommentedFileConfig configInstance, @Nullable Set<Exception> exs) {
        if (enabled) new CounterCommand().register();
    }

    @Override
    public void onUnloaded(CommentedFileConfig configInstance) {
        Bukkit.getCommandMap().getKnownCommands().remove("luminol:tpsbar");
    }
}
