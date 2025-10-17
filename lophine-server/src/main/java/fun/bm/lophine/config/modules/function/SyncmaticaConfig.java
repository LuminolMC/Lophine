package fun.bm.lophine.config.modules.function;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import me.earthme.luminol.config.flags.ConfigInfo;
import org.leavesmc.leaves.protocol.syncmatica.SyncmaticaProtocol;

public class SyncmaticaConfig {
    @ConfigInfo(name = "enabled", comments = """
            Enable Syncmatica protocol support""")
    public static boolean enabled = false;
    @ConfigInfo(name = "useQuota", comments = """
            Is there a limit on the size of projection files?""")
    public static boolean useQuota = false;
    @ConfigInfo(name = "quota-Limit", comments = """
            Maximum Projection File Size (in bytes)""")
    public static int quotaLimit = 40000000;

    public void onLoaded(CommentedFileConfig configInstance) {
        SyncmaticaProtocol.init(enabled);
    }
}
