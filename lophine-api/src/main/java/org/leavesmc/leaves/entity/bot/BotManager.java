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

package org.leavesmc.leaves.entity.bot;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.entity.bot.action.BotAction;

import java.util.Collection;
import java.util.UUID;

/**
 * Simple fakeplayer manager
 */
public interface BotManager {

    /**
     * Gets a fakeplayer object by the given uuid.
     *
     * @param uuid the uuid to look up
     * @return a fakeplayer if one was found, null otherwise
     */
    @Nullable Bot getBot(@NotNull UUID uuid);

    /**
     * Gets a fakeplayer object by the given name.
     *
     * @param name the name to look up
     * @return a fakeplayer if one was found, null otherwise
     */
    @Nullable Bot getBot(@NotNull String name);

    /**
     * Gets a view of all currently logged in fakeplayers. This view is a reused object, making some operations like Collection.size() zero-allocation.
     *
     * @return a view of fakeplayers.
     */
    Collection<Bot> getBots();

    /**
     * Create a bot action by class.
     *
     * @param type action class
     * @return a bot action instance if one was found, null otherwise
     */
    <T extends BotAction<T>> T newAction(@NotNull Class<T> type);

    BotCreator botCreator(@NotNull String rawName, @NotNull Location location);
}
