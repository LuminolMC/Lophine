package fun.bm.lophine.config.modules.function.carpet;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.Locale;

@ConfigClassInfo(
        category = EnumConfigCategory.FUNCTION,
        directory = "carpet-porting",
        name = "carpet-ams",
        comments = """
                Gameplay toggles ported from Carpet-AMS-Addition."""
)
public class CarpetAmsPortingConfig implements IConfigModule {
    @ConfigInfo(name = "nether-water-placement", comments = """
            Ported from Carpet-AMS-Addition.
            Players can place water in ultra-warm dimensions.""")
    public static boolean netherWaterPlacement = false;

    @ConfigInfo(name = "extinguished-campfire", comments = """
            Ported from Carpet-AMS-Addition.
            Newly placed campfires start extinguished instead of lit.""")
    public static boolean extinguishedCampfire = false;

    @ConfigInfo(name = "safe-flight", comments = """
            Ported from Carpet-AMS-Addition.
            Players ignore elytra fly-into-wall damage.""")
    public static boolean safeFlight = false;

    @ConfigInfo(name = "invulnerable", comments = """
            Ported from Carpet-AMS-Addition.
            Players ignore all damage except void damage.""")
    public static boolean invulnerable = false;

    @ConfigInfo(name = "creative-one-hit-kill", comments = """
            Ported from Carpet-AMS-Addition.
            Creative players instantly kill attacked entities. Sneaking also sweeps nearby targets.""")
    public static boolean creativeOneHitKill = false;

    @ConfigInfo(name = "creative-shulker-box-drops-disabled", comments = """
            Ported from Carpet-AMS-Addition.
            Breaking shulker boxes in creative no longer drops the boxed item form.""")
    public static boolean creativeShulkerBoxDropsDisabled = false;

    @ConfigInfo(name = "fake-peace", comments = """
            Ported from Carpet-AMS-Addition.
            Disables natural monster spawning globally or in selected dimensions.
            Supported values: false, true, or a comma-separated dimension list.""")
    public static String fakePeace = "false";

    @ConfigInfo(name = "hopper-suction-disabled", comments = """
            Ported from Carpet-AMS-Addition.
            Hoppers stop pulling items from inventories and loose item entities.""")
    public static boolean hopperSuctionDisabled = false;

    @ConfigInfo(name = "use-item-cooldown-disabled", comments = """
            Ported from Carpet-AMS-Addition.
            Item use cooldowns are ignored for all players.""")
    public static boolean useItemCooldownDisabled = false;

    @ConfigInfo(name = "ender-dragon-no-destroy-block", comments = """
            Ported from Carpet-AMS-Addition.
            The ender dragon no longer breaks blocks while flying through them.""")
    public static boolean enderDragonNoDestroyBlock = false;

    @ConfigInfo(name = "tnt-power-controller", comments = """
            Ported from Carpet-AMS-Addition.
            Overrides primed TNT explosion power. Set to -1.0 to keep vanilla strength.""")
    public static double tntPowerController = -1.0D;

    @ConfigInfo(name = "infinite-trades", comments = """
            Ported from Carpet-AMS-Addition.
            Villager trades never consume uses.""")
    public static boolean infiniteTrades = false;

    @ConfigInfo(name = "easy-max-level-beacon", comments = """
            Ported from Carpet-AMS-Addition.
            A beacon reaches level 4 when the block directly below it is a valid beacon base block.""")
    public static boolean easyMaxLevelBeacon = false;

    @ConfigInfo(name = "easy-compost", comments = """
            Ported from Carpet-AMS-Addition.
            Compostable items always increase the composter level when accepted.""")
    public static boolean easyCompost = false;

    @ConfigInfo(name = "easy-mine-dragon-egg", comments = """
            Ported from Carpet-AMS-Addition.
            Dragon eggs stop teleporting away when broken.""")
    public static boolean easyMineDragonEgg = false;

    @ConfigInfo(name = "undying-coral", comments = """
            Ported from Carpet-AMS-Addition.
            Coral blocks and fans no longer die outside water.""")
    public static boolean undyingCoral = false;

    @ConfigInfo(name = "bamboo-collision-box-disabled", comments = """
            Ported from Carpet-AMS-Addition.
            Bamboo stalks no longer have a collision box.""")
    public static boolean bambooCollisionBoxDisabled = false;

    @ConfigInfo(name = "no-enchanted-golden-apple-eating", comments = """
            Ported from Carpet-AMS-Addition.
            Enchanted golden apples cannot be consumed.""")
    public static boolean noEnchantedGoldenAppleEating = false;

    @ConfigInfo(name = "cake-block-drop-on-break", comments = """
            Ported from Carpet-AMS-Addition.
            Fully intact cakes drop as cake items when broken in survival.""")
    public static boolean cakeBlockDropOnBreak = false;

    @ConfigInfo(name = "no-cake-eating", comments = """
            Ported from Carpet-AMS-Addition.
            Cakes can no longer be eaten directly from the placed block.""")
    public static boolean noCakeEating = false;

    @ConfigInfo(name = "iron-golem-no-drop-flower", comments = """
            Ported from Carpet-AMS-Addition.
            Iron golems no longer drop poppies on death.""")
    public static boolean ironGolemNoDropFlower = false;

    public static boolean canPlaceWaterInUltraWarm(Player player) {
        return netherWaterPlacement && player != null;
    }

    public static boolean shouldFakePeace(ServerLevel level, MobCategory category) {
        if (category != MobCategory.MONSTER) {
            return false;
        }

        String rule = fakePeace == null ? "false" : fakePeace.trim().toLowerCase(Locale.ROOT);
        if (rule.isEmpty() || rule.equals("false")) {
            return false;
        }
        if (rule.equals("true")) {
            return true;
        }

        String dimensionId = level.dimension().identifier().toString().toLowerCase(Locale.ROOT);
        for (String token : rule.split("[,;|\\s]+")) {
            if (token.equals(dimensionId)) {
                return true;
            }
        }
        return false;
    }

    public static float getTntPower(float original) {
        return tntPowerController < 0.0D ? original : (float) tntPowerController;
    }

    public static boolean shouldDropCake(BlockState state) {
        return cakeBlockDropOnBreak
                && state.hasProperty(BlockStateProperties.BITES)
                && state.getValue(BlockStateProperties.BITES) == 0;
    }
}
