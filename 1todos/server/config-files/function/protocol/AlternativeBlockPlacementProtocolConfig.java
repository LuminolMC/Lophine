package fun.bm.lophine.config.modules.function.protocol;

import fun.bm.lophine.enums.EnumAlternativePlaceType;
import me.earthme.luminol.config.IConfigModule;
import me.earthme.luminol.config.flags.ConfigClassInfo;
import me.earthme.luminol.config.flags.ConfigInfo;
import me.earthme.luminol.enums.EnumConfigCategory;

@ConfigClassInfo(category = EnumConfigCategory.FUNCTION, name = "alternative_block_placement", directory = {"protocol"})
public class AlternativeBlockPlacementProtocolConfig implements IConfigModule {
    @ConfigInfo(name = "enabled", comments = """
            Specify the precise placement protocol type
            NONE Disable precise placement protocol
            CARPET Precise placement protocol version 2
            CARPET_FIX Enhanced precise placement protocol version 2 (requires MasaGadget installed on client)
            LITEMATICA Precise placement protocol version 3""")
    public static EnumAlternativePlaceType alternativeBlockPlacement = EnumAlternativePlaceType.NONE;

    public static boolean needIgnoreDistance() {
        return alternativeBlockPlacement != EnumAlternativePlaceType.NONE;
    }
}

