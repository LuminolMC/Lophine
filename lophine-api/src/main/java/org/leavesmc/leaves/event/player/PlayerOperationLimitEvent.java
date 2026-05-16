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

package org.leavesmc.leaves.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a player operation is limited
 */
public class PlayerOperationLimitEvent extends PlayerEvent {
    private static final HandlerList handlers = new HandlerList();

    private final Block block;
    private final Operation operation;

    public PlayerOperationLimitEvent(@NotNull Player who, Operation operation, Block block) {
        super(who);
        this.block = block;
        this.operation = operation;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    /**
     * Gets the operated block
     *
     * @return block
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Gets the type of operation
     *
     * @return operation type
     */
    public Operation getOperation() {
        return operation;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public enum Operation {
        MINE, PLACE
    }
}
