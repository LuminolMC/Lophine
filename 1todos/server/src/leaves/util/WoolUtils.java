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
            entry(Blocks.WHITE_WOOL, DyeColor.WHITE),
            entry(Blocks.ORANGE_WOOL, DyeColor.ORANGE),
            entry(Blocks.MAGENTA_WOOL, DyeColor.MAGENTA),
            entry(Blocks.LIGHT_BLUE_WOOL, DyeColor.LIGHT_BLUE),
            entry(Blocks.YELLOW_WOOL, DyeColor.YELLOW),
            entry(Blocks.LIME_WOOL, DyeColor.LIME),
            entry(Blocks.PINK_WOOL, DyeColor.PINK),
            entry(Blocks.GRAY_WOOL, DyeColor.GRAY),
            entry(Blocks.LIGHT_GRAY_WOOL, DyeColor.LIGHT_GRAY),
            entry(Blocks.CYAN_WOOL, DyeColor.CYAN),
            entry(Blocks.PURPLE_WOOL, DyeColor.PURPLE),
            entry(Blocks.BLUE_WOOL, DyeColor.BLUE),
            entry(Blocks.BROWN_WOOL, DyeColor.BROWN),
            entry(Blocks.GREEN_WOOL, DyeColor.GREEN),
            entry(Blocks.RED_WOOL, DyeColor.RED),
            entry(Blocks.BLACK_WOOL, DyeColor.BLACK)
    );

    public static DyeColor getWoolColorAtPosition(Level worldIn, BlockPos pos) {
        BlockState state = worldIn.getBlockState(pos);
        return WOOL_BLOCK_TO_DYE.get(state.getBlock());
    }
}
