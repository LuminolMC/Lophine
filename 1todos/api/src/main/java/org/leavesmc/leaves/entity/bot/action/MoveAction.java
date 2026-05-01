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

package org.leavesmc.leaves.entity.bot.action;

import org.bukkit.Bukkit;

/**
 * Represents an action for a bot to move to a specific direction.
 */
public interface MoveAction extends StateBotAction<MoveAction> {

    /**
     * Gets the direction of the move action.
     *
     * @return the direction of the move action
     */
    MoveDirection getDirection();

    /**
     * Sets the direction of the move action.
     *
     * @param direction the direction to set
     * @return this action instance
     */
    MoveAction setDirection(MoveDirection direction);

    /**
     * Represents possible movement directions for the bot.
     */
    enum MoveDirection {
        FORWARD("forward"),
        BACKWARD("backward"),
        LEFT("left"),
        RIGHT("right");

        public final String name;

        MoveDirection(String name) {
            this.name = name;
        }
    }

    static MoveAction create() {
        return Bukkit.getBotManager().newAction(MoveAction.class);
    }
}
