package fun.bm.lophine.config.modules.function;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(
    category = EnumConfigCategory.FUNCTION,
    name = "carpet-porting",
    comments = """
            Gameplay toggles ported from fabric-carpet."""
)
public class CarpetPortingConfig implements IConfigModule {
    @ConfigInfo(name = "persistent-parrots", comments = """
            Ported from fabric-carpet.
            Shoulder parrots are no longer dropped by ordinary damage or movement checks.""")
    public static boolean persistentParrots = false;

    @ConfigInfo(name = "xp-no-cooldown", comments = """
            Ported from fabric-carpet.
            Players can pick up experience orbs without the vanilla pickup cooldown.""")
    public static boolean xpNoCooldown = false;

    @ConfigInfo(name = "explosion-no-block-damage", comments = """
            Ported from fabric-carpet.
            Explosions still damage entities, but they no longer break blocks or ignite explosion fire.""")
    public static boolean explosionNoBlockDamage = false;

    @ConfigInfo(name = "xp-from-explosions", comments = """
            Ported from fabric-carpet.
            Blocks destroyed by explosions can still drop experience.""")
    public static boolean xpFromExplosions = false;

    @ConfigInfo(name = "liquid-damage-disabled", comments = """
            Shared by fabric-carpet and carpet-tis-addition.
            Flowing liquids will no longer destroy non-air blocks while spreading.""")
    public static boolean liquidDamageDisabled = false;

    @ConfigInfo(name = "tnt-primer-momentum-removed", comments = """
            Ported from fabric-carpet.
            Newly primed TNT will no longer receive the random lateral launch momentum.""")
    public static boolean tntPrimerMomentumRemoved = false;

    @ConfigInfo(name = "merge-tnt", comments = """
            Ported from fabric-carpet.
            Stationary TNT entities at the same position and fuse can merge into one entity until detonation.""")
    public static boolean mergeTNT = false;

    @ConfigInfo(name = "tnt-do-not-update", comments = """
            Ported from fabric-carpet.
            TNT will not auto-prime from redstone updates when first placed.""")
    public static boolean tntDoNotUpdate = false;

    @ConfigInfo(name = "renewable-sponges", comments = """
            Ported from fabric-carpet.
            Guardians struck by lightning convert into elder guardians.""")
    public static boolean renewableSponges = false;

    @ConfigInfo(name = "renewable-blackstone", comments = """
            Ported from fabric-carpet.
            Lava touching blue ice can form blackstone.""")
    public static boolean renewableBlackstone = false;

    @ConfigInfo(name = "renewable-deepslate", comments = """
            Ported from fabric-carpet.
            Negative-Y lava and water reactions form deepslate variants instead of stone or cobblestone.""")
    public static boolean renewableDeepslate = false;

    @ConfigInfo(name = "desert-shrubs", comments = """
            Ported from fabric-carpet.
            Saplings grown in dry desert conditions turn into dead bushes instead of trees.""")
    public static boolean desertShrubs = false;

    @ConfigInfo(name = "silverfish-drop-gravel", comments = """
            Ported from fabric-carpet.
            Breaking infested blocks also drops a gravel item.""")
    public static boolean silverFishDropGravel = false;

    @ConfigInfo(name = "creative-players-load-chunks", comments = """
            Ported from fabric-carpet.
            Whether creative players should continue to load and tick chunks around them.""")
    public static boolean creativePlayersLoadChunks = true;

    @ConfigInfo(name = "lightning-kills-drops-fix", comments = """
            Ported from fabric-carpet.
            Newly dropped items survive lightning for their first few ticks.""")
    public static boolean lightningKillsDropsFix = false;
}
