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

package org.leavesmc.leaves.protocol.servux.litematics.selection;

import net.minecraft.core.BlockBox;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

public record Box(@Nullable BlockPos pos1, @Nullable BlockPos pos2, String name) {
    public Box() {
        this(BlockPos.ZERO, BlockPos.ZERO, "Unnamed");
    }

    @Nullable
    public BlockBox toVanilla() {
        if (pos1 != null && pos2 != null) {
            return new BlockBox(pos1, pos2);
        }
        return null;
    }
}
