package fun.bm.lophine.config.modules.misc;

import me.earthme.luminol.config.EnumConfigCategory;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigInfo;

public class OldFeatureConfig implements IConfigModule {
    @ConfigInfo(baseName = "spawn_invulnerable_time")
    public static boolean spawnInvulnerableTime = false;

    @ConfigInfo(baseName = "old_zombie_reinforcement")
    public static boolean oldZombieReinforcement = false;

    @ConfigInfo(baseName = "old_explosion_damage_calculator")
    public static boolean oldExplosionDamageCalculator = false;

    @Override
    public EnumConfigCategory getCategory() {
        return EnumConfigCategory.MISC;
    }

    @Override
    public String getBaseName() {
        return "old-feature";
    }
}