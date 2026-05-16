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
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Represents an action for a bot to look at a specific position or player.
 * <p>
 * If both a position and a player are set, the player target takes precedence.
 */
public interface LookAction extends BotAction<LookAction> {

    /**
     * Sets the position in the world for the bot to look at.
     * <p>
     * If a player target is set via {@link #setTarget(Player)}, the bot will look at the player instead of this position.
     *
     * @param pos the {@link Vector} representing the position to look at
     * @return this {@code LookAction} instance for method chaining
     */
    LookAction setPos(Vector pos);

    /**
     * Gets the position in the world that the bot is set to look at.
     * <p>
     * If a player target is set, this value may be ignored.
     *
     * @return the {@link Vector} position to look at, or {@code null} if not set
     */
    Vector getPos();

    /**
     * Sets the player for the bot to look at.
     * <p>
     * When a player is set as the target, the bot will continuously look at the player's current position,
     * overriding any position set by {@link #setPos(Vector)}.
     *
     * @param player the {@link Player} to look at, or {@code null} to clear the target
     * @return this {@code LookAction} instance for method chaining
     */
    LookAction setTarget(Player player);

    /**
     * Gets the player that the bot is set to look at.
     *
     * @return the {@link Player} target, or {@code null} if not set
     */
    Player getTarget();

    static LookAction create() {
        return Bukkit.getBotManager().newAction(LookAction.class);
    }
}
