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

package org.leavesmc.leaves.event.bot;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.entity.bot.Bot;

public class BotSpawnLocationEvent extends BotEvent {

    private static final HandlerList handlers = new HandlerList();

    private Location spawnLocation;

    public BotSpawnLocationEvent(@NotNull final Bot who, @NotNull Location spawnLocation) {
        super(who);
        this.spawnLocation = spawnLocation;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(@NotNull Location location) {
        this.spawnLocation = location;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
