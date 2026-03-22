package fun.bm.lophine.config.carpet.modules;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(
        category = EnumConfigCategory.ROOT,
        name = "fakeplayer",
        directory = {"carpet"},
        comments = """
                Carpet fakeplayer compatibility mapped onto Lophine fakeplayers.
                commandPlayer is currently backed by Lophine's /bot command surface."""
)
public class FakePlayerCompatConfig implements IConfigModule {
    @ConfigInfo(name = "commandBot", comments = """
            Enable Lophine's /bot command.""")
    public static boolean commandBot = false;

    @ConfigInfo(name = "commandPlayer", comments = """
            Map Carpet's commandPlayer rule to the same fakeplayer command surface used by /bot.""")
    public static boolean commandPlayer = false;

    @ConfigInfo(name = "fakePlayerResident", comments = """
            Keep fakeplayers resident across unload and restart.""")
    public static boolean fakePlayerResident = false;

    @ConfigInfo(name = "openFakePlayerInventory", comments = """
            Allow opening fakeplayer inventories.""")
    public static boolean openFakePlayerInventory = false;

    @ConfigInfo(name = "fakePlayerTicksLikeRealPlayer", comments = """
            Tick fakeplayers in the network phase to better match real player timing.""")
    public static boolean fakePlayerTicksLikeRealPlayer = false;

    @ConfigInfo(name = "fakePlayerDefaultSurvivalMode", comments = """
            Force newly created fakeplayers to start in survival instead of the server default gamemode.""")
    public static boolean fakePlayerDefaultSurvivalMode = false;

    @ConfigInfo(name = "fakePlayerInteractLikeClient", comments = """
            Make fakeplayer entity interaction follow client-side fallback behavior more closely.""")
    public static boolean fakePlayerInteractLikeClient = false;

    @ConfigInfo(name = "fakePlayerAutoReplaceTool", comments = """
            Toggle automatic tool replacement for fakeplayers.""")
    public static boolean fakePlayerAutoReplaceTool = false;

    @ConfigInfo(name = "fakePlayerAutoReplenishment", comments = """
            Toggle automatic stack replenishment for fakeplayers.""")
    public static boolean fakePlayerAutoReplenishment = false;

    @ConfigInfo(name = "fakePlayerAutoReplenishmentFormShulkerBox", comments = """
            Let fakeplayer replenishment pull matching items out of shulker boxes in the inventory.""")
    public static boolean fakePlayerAutoReplenishmentFormShulkerBox = false;

    @ConfigInfo(name = "fakePlayerAutoFish", comments = """
            Let fakeplayers holding a fishing rod automatically cast and reel it in.""")
    public static boolean fakePlayerAutoFish = false;

    @ConfigInfo(name = "fakePlayerReloadAction", comments = """
            Persist queued fakeplayer actions across save and reload.""")
    public static boolean fakePlayerReloadAction = false;
}
