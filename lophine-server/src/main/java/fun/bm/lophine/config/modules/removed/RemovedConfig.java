package fun.bm.lophine.config.modules.removed;

import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.config.flags.TransformedConfig;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.REMOVED, name = "removed_config")
public class RemovedConfig implements IConfigModule {
    @TransformedConfig(name = "optimizedTable", directory = {"optimizations", "waypoint"}, transform = false)
    @TransformedConfig(name = "enabled", directory = {"fixes", "end-void-ring"}, transform = false)
    @TransformedConfig(name = "enabled", directory = {"gameplay", "end_void_rings"}, transform = false)
    @TransformedConfig(name = "datapack_command_enabled", directory = {"experiment", "command"}, transform = false)
    @TransformedConfig(name = "disable_end_crystal_check", directory = {"fixes", "end_crystal"}, transform = false)
    @TransformedConfig(name = "disable_end_crystal_check", directory = {"misc", "end_crystal"}, transform = false)
    @TransformedConfig(name = "allow_skip_cooldown", directory = {"misc", "revert_raid_changes"}, transform = false)
    @TransformedConfig(name = "bad_omen_infinite", directory = {"misc", "revert_raid_changes"}, transform = false)
    @TransformedConfig(name = "skip_height_check", directory = {"misc", "revert_raid_changes"}, transform = false)
    @TransformedConfig(name = "skip_self_raid_check", directory = {"misc", "revert_raid_changes"}, transform = false)
    @TransformedConfig(name = "use_old_position_find", directory = {"misc", "revert_raid_changes"}, transform = false)
    @TransformedConfig(name = "vanilla_hopper", directory = {"misc", "redstone"}, transform = false)
    @TransformedConfig(name = "old_replaceable_by_mushrooms", directory = {"misc", "old-feature"}, transform = false)
    @TransformedConfig(name = "old_nether_portal_collision", directory = {"misc", "old-feature"}, transform = false)
    @TransformedConfig(name = "better_shulker_box", directory = {"misc", "container_expansion"}, transform = false)
    @ConfigInfo(name = "removed", comments =
            """
                    RemovedConfig redirect to here, no any function.""")
    public static boolean enabled = true;
}