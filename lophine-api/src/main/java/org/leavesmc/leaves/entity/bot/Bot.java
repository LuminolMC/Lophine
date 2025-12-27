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

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.entity.bot.action.BotAction;

import java.util.UUID;

/**
 * Represents a fakeplayer
 */
public interface Bot extends Player {

    /**
     * Gets the fakeplayer skin
     *
     * @return fakeplayer skin name
     */
    @Nullable String getSkinName();

    /**
     * Gets the fakeplayer name without prefix and suffix
     *
     * @return fakeplayer raw name
     */
    @NotNull String getRawName();

    /**
     * Gets the creator's UUID of the fakeplayer
     *
     * @return creator's UUID
     */
    @Nullable UUID getCreatePlayerUUID();

    /**
     * Add an action to the fakeplayer
     *
     * @param action bot action
     */
    <T extends BotAction<T>> void addAction(@NotNull T action);

    /**
     * Get the copy action in giving index
     *
     * @param index index of actions
     * @return Action of that index
     */
    BotAction<?> getAction(int index);

    /**
     * Get action size
     *
     * @return size
     */
    int getActionSize();

    /**
     * Stop the action in giving index
     *
     * @param index index of actions
     */
    void stopAction(int index);

    /**
     * Stop all the actions of the fakeplayer
     */
    void stopAllActions();

    /**
     * Remove the fakeplayer
     *
     * @param save should save
     * @return success
     */
    boolean remove(boolean save);

    /**
     * Remove the fakeplayer
     *
     * @param save   should save
     * @param resume should resume at next server start
     * @return success
     */
    boolean remove(boolean save, boolean resume);
}
