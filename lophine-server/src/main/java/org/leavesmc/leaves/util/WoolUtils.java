/*
 * This file is part of Leaves (https://github.com/LeavesMC/Leaves)
 *
 * Leaves is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Leaves is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Leaves. If not, see <https://www.gnu.org/licenses/>.
 */

package org.leavesmc.leaves.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

import static java.util.Map.entry;

public class WoolUtils {
    private static final Map<Block, DyeColor> WOOL_BLOCK_TO_DYE = Map.ofEntries(
            entry(Blocks.WOOL.white(), DyeColor.WHITE),
            entry(Blocks.WOOL.orange(), DyeColor.ORANGE),
            entry(Blocks.WOOL.magenta(), DyeColor.MAGENTA),
            entry(Blocks.WOOL.lightBlue(), DyeColor.LIGHT_BLUE),
            entry(Blocks.WOOL.yellow(), DyeColor.YELLOW),
            entry(Blocks.WOOL.lime(), DyeColor.LIME),
            entry(Blocks.WOOL.pink(), DyeColor.PINK),
            entry(Blocks.WOOL.gray(), DyeColor.GRAY),
            entry(Blocks.WOOL.lightGray(), DyeColor.LIGHT_GRAY),
            entry(Blocks.WOOL.cyan(), DyeColor.CYAN),
            entry(Blocks.WOOL.purple(), DyeColor.PURPLE),
            entry(Blocks.WOOL.blue(), DyeColor.BLUE),
            entry(Blocks.WOOL.brown(), DyeColor.BROWN),
            entry(Blocks.WOOL.green(), DyeColor.GREEN),
            entry(Blocks.WOOL.red(), DyeColor.RED),
            entry(Blocks.WOOL.black(), DyeColor.BLACK)
    );

    public static DyeColor getWoolColorAtPosition(Level worldIn, BlockPos pos) {
        BlockState state = worldIn.getBlockState(pos);
        return WOOL_BLOCK_TO_DYE.get(state.getBlock());
    }
}
