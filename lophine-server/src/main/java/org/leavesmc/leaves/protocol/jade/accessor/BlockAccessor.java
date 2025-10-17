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

package org.leavesmc.leaves.protocol.jade.accessor;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public interface BlockAccessor extends Accessor<BlockHitResult> {

    Block getBlock();

    BlockState getBlockState();

    BlockEntity getBlockEntity();

    BlockPos getPosition();

    @ApiStatus.NonExtendable
    interface Builder {
        Builder level(ServerLevel level);

        Builder player(Player player);

        Builder hit(BlockHitResult hit);

        Builder blockState(BlockState state);

        default Builder blockEntity(BlockEntity blockEntity) {
            return blockEntity(() -> blockEntity);
        }

        Builder blockEntity(Supplier<BlockEntity> blockEntity);

        Builder from(BlockAccessor accessor);

        BlockAccessor build();
    }
}
