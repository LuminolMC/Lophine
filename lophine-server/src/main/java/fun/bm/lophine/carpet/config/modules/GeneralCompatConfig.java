package fun.bm.lophine.carpet.config.modules;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import fun.bm.lophine.carpet.RedirectConfig;
import me.earthme.luminol.config.ConfigManager;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;
import me.earthme.luminol.enums.EnumConfigCategory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

@ConfigClassInfo(
        category = EnumConfigCategory.ROOT,
        name = "general",
        directory = {"carpet"},
        comments = """
                Carpet/AMS/TIS/Org compatibility rules backed by existing Lophine features.
                Only rules that already have a working server-side implementation are exposed here."""
)
public class GeneralCompatConfig implements IConfigModule {
    @TransformedConfig(name = "language", directory = {"carpet", "general"}, transformComments = false)
    @ConfigInfo(name = "language", comments = """
            Carpet language value.
            ATTENTION: This config will not update in Lophine global now!""")
    public static String language = "en_us";

    @ConfigInfo(name = "amsUpdateSuppressionCrashFix", comments = """
            Update suppression crash protection.""")
    public static boolean amsUpdateSuppressionCrashFix = false;

    @ConfigInfo(name = "yeetUpdateSuppressionCrash", comments = """
            Update suppression crash yeeting.""")
    public static boolean yeetUpdateSuppressionCrash = false;

    @ConfigInfo(name = "dustTrapdoorReintroduced", comments = """
            Map dust-on-open-trapdoor behavior to Lophine's redstone-ignore-upwards-update option.""")
    public static boolean dustTrapdoorReintroduced = false;

    @ConfigInfo(name = "shulkerBoxCCEReintroduced", comments = """
            Map shulker-box CCE update suppression to Lophine's cce-update-suppression option.""")
    public static boolean shulkerBoxCCEReintroduced = false;

    @ConfigInfo(name = "instantBlockUpdaterReintroduced", comments = """
            Enable the existing instant block updater patch already carried by Lophine.""")
    public static boolean instantBlockUpdaterReintroduced = false;

    @ConfigInfo(name = "commandTick", comments = """
            Enable the tick command support already patched into Lophine.""")
    public static boolean commandTick = false;

    @ConfigInfo(name = "creativeNoClip", comments = """
            Enable the existing creative fly no clip implementation.""")
    public static boolean creativeNoClip = false;

    @ConfigInfo(name = "optimizedDragonRespawn", comments = """
            Enable the existing optimized dragon respawn implementation from Luminol.""")
    public static boolean optimizedDragonRespawn = false;

    @ConfigInfo(name = "antiSpamDisabled", comments = """
            Disable the server-side chat and creative-drop spam throttles used by vanilla/Spigot.""")
    public static boolean antiSpamDisabled = false;

    @ConfigInfo(name = "blockPlacementIgnoreEntity", comments = """
            Allow creative players to place blocks without entity collision checks.""")
    public static boolean blockPlacementIgnoreEntity = false;

    @ConfigInfo(name = "creativeOpenContainerForcibly", comments = """
            Allow creative players to forcibly open blocked chests, ender chests and shulker boxes.""")
    public static boolean creativeOpenContainerForcibly = false;

    @ConfigInfo(name = "creativeOneHitKill", comments = """
            Allow creative players to instantly kill attackable non-creative, non-spectator entities.
            Sneaking expands the effect into a small area attack.""")
    public static boolean creativeOneHitKill = false;

    @ConfigInfo(name = "observerNoDetection", comments = """
            Disable observer detection pulses entirely.""")
    public static boolean observerNoDetection = false;

    @ConfigInfo(name = "bambooModelNoOffset", comments = """
            Remove the random horizontal model offset from bamboo and bamboo saplings.""")
    public static boolean bambooModelNoOffset = false;

    @ConfigInfo(name = "creativeNoItemCooldown", comments = """
            Skip item cooldown application for creative players.""")
    public static boolean creativeNoItemCooldown = false;

    @ConfigInfo(name = "ctrlQCraftingFix", comments = """
            Compatibility flag for the upstream result-slot Ctrl+Q crafting fix already present in the current menu code.""")
    public static boolean ctrlQCraftingFix = false;

    @ConfigInfo(name = "carpetAlwaysSetDefault", comments = """
            Compatibility flag for Lophine's config loader, which already writes default values into the compat config during preload.""")
    public static boolean carpetAlwaysSetDefault = false;

    @ConfigInfo(name = "placementRotationFix", comments = """
            Use the player's main body rotation for placement direction checks instead of interpolated head yaw.""")
    public static boolean placementRotationFix = false;

    @ConfigInfo(name = "tntDoNotUpdate", comments = """
            Prevent TNT from checking redstone power when first placed.""")
    public static boolean tntDoNotUpdate = false;

    @ConfigInfo(name = "totallyNoBlockUpdate", comments = """
            Suppress neighbor and shape updates globally for block changes.""")
    public static boolean totallyNoBlockUpdate = false;

    @ConfigInfo(name = "tiscmNetworkProtocol", comments = """
            Enable the native Carpet TIS Addition network channel on `tiscm:network/v1`.""")
    public static boolean tiscmNetworkProtocol = false;

    @ConfigInfo(name = "hopperNoItemCost", comments = """
            Restore the transferred stack into a hopper when a wool block is placed on top of it.""")
    public static boolean hopperNoItemCost = false;

    @ConfigInfo(name = "explosionNoBlockDamage", comments = """
            Let explosions damage entities without breaking blocks.""")
    public static boolean explosionNoBlockDamage = false;

    @ConfigInfo(name = "noCreeperBlockBreaking", comments = """
            Disables creeper explosion block breaking.""")
    public static boolean noCreeperBlockBreaking = false;

    @ConfigInfo(name = "noGhastBlockBreaking", comments = """
            Disables ghast fireball explosion block breaking.""")
    public static boolean noGhastBlockBreaking = false;

    @ConfigInfo(name = "disableBlazeFire", comments = """
            Disables fire made from blaze fireballs.""")
    public static boolean disableBlazeFire = false;

    @ConfigInfo(name = "disableGhastFire", comments = """
            Disables fire made from ghast fireballs.""")
    public static boolean disableGhastFire = false;

    @ConfigInfo(name = "optimizedTNTHighPriority", comments = """
            Compatibility flag for the already optimized server explosion path carried by the current runtime.""")
    public static boolean optimizedTNTHighPriority = false;

    @ConfigInfo(name = "tntPrimerMomentumRemoved", comments = """
            Remove the random horizontal launch momentum from newly primed TNT.""")
    public static boolean tntPrimerMomentumRemoved = false;

    @ConfigInfo(name = "tntIgnoreRedstoneSignal", comments = """
            Ignore redstone power when deciding whether TNT should auto-prime.""")
    public static boolean tntIgnoreRedstoneSignal = false;

    @ConfigInfo(name = "tntDupingFix", comments = """
            Toggle the piston desync path used by vanilla TNT duplication setups.""")
    public static boolean tntDupingFix = false;

    @ConfigInfo(name = "interactionUpdates", comments = """
            Control whether player interaction block changes emit normal block updates.
            Set to false to suppress neighbor and shape updates during block use and breaking.""")
    public static boolean interactionUpdates = true;

    @ConfigInfo(name = "xpNoCooldown", comments = """
            Allow players to absorb multiple experience orbs in the same tick without pickup delay.""")
    public static boolean xpNoCooldown = false;

    @ConfigInfo(name = "powerfulExpMending", comments = """
            Let picked-up experience repair all damaged mending items in the player's inventory, not only equipped gear.""")
    public static boolean powerfulExpMending = false;

    @ConfigInfo(name = "clientSettingsLostOnRespawnFix", comments = """
            Reapply the player's last known client settings after respawn.""")
    public static boolean clientSettingsLostOnRespawnFix = false;

    @ConfigInfo(name = "sensibleEnderman", comments = """
            Restrict enderman block pickup to pumpkins and melons only.""")
    public static boolean sensibleEnderman = false;

    @ConfigInfo(name = "entityInstantDeathRemoval", comments = """
            Remove the normal 20gt delay before dead living entities are discarded.""")
    public static boolean entityInstantDeathRemoval = false;

    @ConfigInfo(name = "farmlandTrampledDisabled", comments = """
            Prevent farmland from turning into dirt when entities land on it.""")
    public static boolean farmlandTrampledDisabled = false;

    @ConfigInfo(name = "shulkerGolem", comments = """
            Allow a carved pumpkin on top of a shulker box to summon a shulker.""")
    public static boolean shulkerGolem = false;

    @ConfigInfo(name = "preventEndSpikeRespawn", comments = """
            Skip obsidian spike regeneration during dragon respawn.""")
    public static boolean preventEndSpikeRespawn = false;

    @ConfigInfo(name = "yeetOutOfOrderChatKick", comments = """
            Ignore out-of-order secure chat chain checks instead of invalidating the chat session.""")
    public static boolean yeetOutOfOrderChatKick = false;

    @ConfigInfo(name = "betterCraftableBoneBlock", comments = """
            Add the AMS alternate bone block recipe that yields 3 bone blocks from 9 bones.""")
    public static boolean betterCraftableBoneBlock = false;

    @ConfigInfo(name = "betterCraftableDispenser", comments = """
            Add the AMS alternate dispenser recipes using a dropper.""")
    public static boolean betterCraftableDispenser = false;

    @ConfigInfo(name = "viewDistance", comments = """
            Override the dedicated server's startup view distance with the Carpet-compatible value.""")
    public static int viewDistance = 12;

    @ConfigInfo(name = "tickCommandPermission", comments = """
            Override the `/tick` command permission level.
            Accepts values in the range 0..4, where 2 matches old Carpet behavior and 3 keeps vanilla.""")
    public static int tickCommandPermission = 3;

    @ConfigInfo(name = "tickFreezeCommandToggleable", comments = """
            Make `/tick freeze` toggle back to running when executed while the server is already frozen.""")
    public static boolean tickFreezeCommandToggleable = false;

    @ConfigInfo(name = "syncServerMsptMetricsData", comments = """
            Broadcast live MSPT samples through the native TISCM protocol channel.""")
    public static boolean syncServerMsptMetricsData = false;

    @ConfigInfo(name = "simpleInGameCalculator", comments = """
            Evaluate chat messages prefixed with `=` as a simple calculator expression and reply privately.""")
    public static boolean simpleInGameCalculator = false;

    @ConfigInfo(name = "microTiming", comments = """
            Compatibility flag for the built-in region profiler and timing instrumentation carried by Folia/Moonrise.""")
    public static boolean microTiming = false;

    @ConfigInfo(name = "fastRedstoneDust", comments = """
            Route redstone dust updates through the Alternate Current fast-update backend.""")
    public static boolean fastRedstoneDust = false;

    @ConfigInfo(name = "lagFreeSpawning", comments = """
            Use the lightweight collision and precooked-mob spawning path for natural spawning checks.""")
    public static boolean lagFreeSpawning = false;

    @ConfigInfo(name = "optimizedFastEntityMovement", comments = """
            Compatibility flag for the always-on Moonrise/Paper fast entity movement collision pipeline.""")
    public static boolean optimizedFastEntityMovement = false;

    @ConfigInfo(name = "optimizedHardHitBoxEntityCollision", comments = """
            Compatibility flag for the always-on Moonrise/Paper hard-hitbox entity collision optimizations.""")
    public static boolean optimizedHardHitBoxEntityCollision = false;

    @ConfigInfo(name = "tntFuseDuration", comments = """
            Override the default primed TNT fuse duration in ticks.
            Accepts values in the range 0..32767.""")
    public static int tntFuseDuration = 80;

    @ConfigInfo(name = "defaultLoggers", comments = """
            Carpet-style default logger subscriptions for players.
            Examples: ["tps", "mob_caps", "counter white"]""")
    public static List<String> defaultLoggers = List.of();

    public static boolean mergedUpdateSuppressionCrashEnabled() {
        return amsUpdateSuppressionCrashFix || yeetUpdateSuppressionCrash;
    }

    public static int normalizedTntFuseDuration() {
        return Math.clamp(tntFuseDuration, 0, Short.MAX_VALUE);
    }

    public static int normalizedTickCommandPermission() {
        return Math.clamp(tickCommandPermission, 0, 4);
    }

    // Should remove in next Minecraft version update
    @Override
    public void onLoaded(CommentedFileConfig configInstance, @Nullable Set<Exception> e) {
        ConfigManager.registerRunnableBeforeFinalLoad(RedirectConfig::redirect);
    }
}
