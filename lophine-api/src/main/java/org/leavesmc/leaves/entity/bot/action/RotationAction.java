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
 * Represents an action for a bot to rotate to a specific yaw and pitch.
 */
public interface RotationAction extends BotAction<RotationAction> {

    /**
     * Sets the yaw of the rotation.
     *
     * @param yaw the yaw to set
     * @return this action instance
     */
    RotationAction setYaw(float yaw);

    /**
     * Sets the pitch of the rotation.
     *
     * @param pitch the pitch to set
     * @return this action instance
     */
    RotationAction setPitch(float pitch);

    /**
     * Gets the yaw of the rotation.
     *
     * @return the yaw
     */
    float getYaw();

    /**
     * Gets the pitch of the rotation.
     *
     * @return the pitch
     */
    float getPitch();

    static RotationAction create() {
        return Bukkit.getBotManager().newAction(RotationAction.class);
    }
}
