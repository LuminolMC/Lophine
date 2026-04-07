package fun.bm.lophine.carpet.config.modules;

import fun.bm.lophine.carpet.CarpetCompatSync;
import me.earthme.luminol.config.IConfigModule;

public class CoreConfig implements IConfigModule {
    @Override
    public void beforeFinalLoad() {
        CarpetCompatSync.apply();
    }
}
