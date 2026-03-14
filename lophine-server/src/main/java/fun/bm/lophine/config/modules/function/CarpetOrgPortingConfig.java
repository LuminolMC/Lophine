package fun.bm.lophine.config.modules.function;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(
    category = EnumConfigCategory.FUNCTION,
    name = "carpet-org-porting",
    comments = """
            Gameplay toggles ported from Carpet-Org-Addition."""
)
public class CarpetOrgPortingConfig implements IConfigModule {
    @ConfigInfo(name = "disable-bat-can-spawn", comments = """
            Ported from Carpet-Org-Addition.
            Prevents bats from spawning naturally.""")
    public static boolean disableBatCanSpawn = false;

    @ConfigInfo(name = "disable-water-freezes", comments = """
            Ported from Carpet-Org-Addition.
            Prevents water from freezing into ice.""")
    public static boolean disableWaterFreezes = false;

    @ConfigInfo(name = "not-damage-ender-pearl", comments = """
            Ported from Carpet-Org-Addition.
            Ender pearl teleports no longer damage the thrower.""")
    public static boolean notDamageEnderPearl = false;

    @ConfigInfo(name = "riptide-ignore-weather", comments = """
            Ported from Carpet-Org-Addition.
            Riptide tridents can be used without water or rain.""")
    public static boolean riptideIgnoreWeather = false;

    @ConfigInfo(name = "channeling-ignore-weather", comments = """
            Ported from Carpet-Org-Addition.
            Channeling tridents can summon lightning even when it is not thundering.""")
    public static boolean channelingIgnoreWeather = false;

    @ConfigInfo(name = "disable-furnace-drop-experience", comments = """
            Ported from Carpet-Org-Addition.
            Furnaces no longer drop experience when recipes are taken out.""")
    public static boolean disableFurnaceDropExperience = false;

    @ConfigInfo(name = "disable-mob-peaceful-despawn", comments = """
            Ported from Carpet-Org-Addition.
            Persistent mobs are not removed just because the world switches to peaceful.""")
    public static boolean disableMobPeacefulDespawn = false;

    @ConfigInfo(name = "disable-respawn-blocks-explode", comments = """
            Ported from Carpet-Org-Addition.
            Beds and respawn anchors no longer explode in invalid dimensions.""")
    public static boolean disableRespawnBlocksExplode = false;

    @ConfigInfo(name = "can-mine-spawner", comments = """
            Ported from Carpet-Org-Addition.
            Silk Touch can harvest spawners while preserving their spawn data.""")
    public static boolean canMineSpawner = false;

    @ConfigInfo(name = "protection-enchantment-compatible", comments = """
            Ported from Carpet-Org-Addition.
            Different protection enchantments can coexist on the same item.""")
    public static boolean protectionEnchantmentCompatible = false;

    @ConfigInfo(name = "damage-enchantment-compatible", comments = """
            Ported from Carpet-Org-Addition.
            Different damage enchantments can coexist on the same weapon.""")
    public static boolean damageEnchantmentCompatible = false;

    @ConfigInfo(name = "set-anvil-cost-limit", comments = """
            Ported from Carpet-Org-Addition.
            Overrides the anvil too-expensive threshold. Use -1 to keep vanilla behavior.""")
    public static int setAnvilCostLimit = -1;

    @ConfigInfo(name = "firework-rocket-use-cooldown", comments = """
            Ported from Carpet-Org-Addition.
            Adds a short cooldown after using fireworks from blocks or during elytra flight.""")
    public static boolean fireworkRocketUseCooldown = false;

    @ConfigInfo(name = "open-shulker-box-forcibly", comments = """
            Ported from Carpet-Org-Addition.
            Allows shulker boxes to open even when their lid would normally be blocked.""")
    public static boolean openShulkerBoxForcibly = false;

    public static int getAnvilCostLimit(int original) {
        return setAnvilCostLimit < 0 ? original : Math.max(1, setAnvilCostLimit);
    }
}
