package fun.bm.lophine.config.modules.function.carpet;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.Locale;

@ConfigClassInfo(
        category = EnumConfigCategory.FUNCTION,
        directory = "carpet-porting",
        name = "carpet-tis",
        comments = """
                Gameplay toggles ported from carpet-tis-addition."""
)
public class CarpetTisPortingConfig implements IConfigModule {
    @ConfigInfo(name = "farmland-trampled-disabled", comments = """
            Ported from carpet-tis-addition.
            Farmland will not turn back into dirt when entities land on it.""")
    public static boolean farmlandTrampledDisabled = false;

    @ConfigInfo(name = "turtle-egg-trampled-disabled", comments = """
            Ported from carpet-tis-addition.
            Turtle eggs will no longer be broken by stepping, falling or zombie egg-breaking AI.""")
    public static boolean turtleEggTrampledDisabled = false;

    @ConfigInfo(name = "creative-open-container-forcibly", comments = """
            Ported from carpet-tis-addition.
            Creative players can open chests, ender chests and shulker boxes even when obstructed.""")
    public static boolean creativeOpenContainerForcibly = false;

    @ConfigInfo(name = "block-placement-ignore-entity", comments = """
            Ported from carpet-tis-addition.
            Creative players can place blocks without entity collision preventing placement.""")
    public static boolean blockPlacementIgnoreEntity = false;

    @ConfigInfo(name = "entity-placement-ignore-collision", comments = """
            Ported from carpet-tis-addition.
            Ignores entity collision checks when placing boats, armor stands, and end crystals.""")
    public static boolean entityPlacementIgnoreCollision = false;

    @ConfigInfo(name = "creative-nether-water-placement", comments = """
            Ported from carpet-tis-addition.
            Creative players can place water in ultra-warm dimensions.""")
    public static boolean creativeNetherWaterPlacement = false;

    @ConfigInfo(name = "creative-no-item-cooldown", comments = """
            Ported from carpet-tis-addition.
            Creative players do not receive item cooldowns.""")
    public static boolean creativeNoItemCooldown = false;

    @ConfigInfo(name = "dispenser-no-item-cost", comments = """
            Ported from carpet-tis-addition.
            Dispensers and item-dispensing droppers use a copy of the stack instead of consuming it.""")
    public static boolean dispenserNoItemCost = false;

    @ConfigInfo(name = "instant-command-block", comments = """
            Ported from carpet-tis-addition.
            Redstone command blocks placed on redstone ore execute immediately instead of waiting one tick.""")
    public static boolean instantCommandBlock = false;

    @ConfigInfo(name = "end-portal-opened-sound-disabled", comments = """
            Ported from carpet-tis-addition.
            Disables the global portal-opening sound when an end portal is completed.""")
    public static boolean endPortalOpenedSoundDisabled = false;

    @ConfigInfo(name = "wither-spawned-sound-disabled", comments = """
            Ported from carpet-tis-addition.
            Disables the global wither spawn sound broadcast.""")
    public static boolean witherSpawnedSoundDisabled = false;

    @ConfigInfo(name = "item-entity-skip-movement-disabled", comments = """
            Ported from carpet-tis-addition.
            Item entities update their movement every tick instead of every fourth tick when idle.""")
    public static boolean itemEntitySkipMovementDisabled = false;

    @ConfigInfo(name = "observer-no-detection", comments = """
            Ported from carpet-tis-addition.
            Observers will stop scheduling output pulses when they detect updates.""")
    public static boolean observerNoDetection = false;

    @ConfigInfo(name = "repeater-half-delay", comments = """
            Ported from carpet-tis-addition.
            Repeaters placed on redstone ore run at half their normal delay, with a minimum of 1 tick.""")
    public static boolean repeaterHalfDelay = false;

    @ConfigInfo(name = "tnt-fuse-duration", comments = """
            Ported from carpet-tis-addition.
            Sets the default TNT fuse duration in ticks.""")
    public static int tntFuseDuration = 80;

    @ConfigInfo(name = "undead-dont-burn-in-sunlight", comments = """
            Ported from carpet-tis-addition.
            Daylight-burning undead mobs will no longer ignite in sunlight.""")
    public static boolean undeadDontBurnInSunlight = false;

    @ConfigInfo(name = "void-damage-amount", comments = """
            Ported from carpet-tis-addition.
            Overrides void damage dealt to living entities each tick. Uses the world setting when left at 4.0.""")
    public static double voidDamageAmount = 4.0D;

    @ConfigInfo(name = "void-damage-ignore-player", comments = """
            Ported from carpet-tis-addition.
            Controls which players ignore void damage. Supported values: false, true, or a list like survival,creative.""")
    public static String voidDamageIgnorePlayer = "false";

    @ConfigInfo(name = "void-related-altitude", comments = """
            Ported from carpet-tis-addition.
            Overrides the Y level where entities start being treated as below the world.""")
    public static double voidRelatedAltitude = -64.0D;

    @ConfigInfo(name = "xp-tracking-distance", comments = """
            Ported from carpet-tis-addition.
            Sets how far experience orbs can search for and accelerate toward players.""")
    public static double xpTrackingDistance = 8.0D;

    public static boolean canCreativeOpenContainer(Player player) {
        return creativeOpenContainerForcibly && player.isCreative();
    }

    public static int getTntFuseDuration() {
        return Math.max(1, tntFuseDuration);
    }

    public static double getXpTrackingDistance() {
        return Math.max(0.0D, xpTrackingDistance);
    }

    public static double getXpTrackingDistanceSqr() {
        double distance = getXpTrackingDistance();
        return distance * distance;
    }

    public static double getVoidRelatedAltitude(Level level) {
        return voidRelatedAltitude == -64.0D
                ? level.getMinY() + level.getWorld().getVoidDamageMinBuildHeightOffset()
                : voidRelatedAltitude;
    }

    public static float getVoidDamageAmount(Level level) {
        return voidDamageAmount == 4.0D ? (float) level.getWorld().getVoidDamageAmount() : (float) voidDamageAmount;
    }

    public static boolean shouldIgnoreVoidDamage(Player player) {
        String rule = voidDamageIgnorePlayer == null ? "false" : voidDamageIgnorePlayer.trim().toLowerCase(Locale.ROOT);
        if (rule.isEmpty() || rule.equals("false")) {
            return false;
        }
        if (rule.equals("true")) {
            return true;
        }

        String gameModeName;
        if (player instanceof ServerPlayer serverPlayer) {
            gameModeName = serverPlayer.gameMode.getGameModeForPlayer().getName().toLowerCase(Locale.ROOT);
        } else if (player.isSpectator()) {
            gameModeName = "spectator";
        } else if (player.isCreative()) {
            gameModeName = "creative";
        } else {
            gameModeName = "survival";
        }

        for (String token : rule.split("[,;|\\s]+")) {
            if (token.equals(gameModeName)) {
                return true;
            }
        }
        return false;
    }
}
