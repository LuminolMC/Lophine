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

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public interface BotCreator {

    static BotCreator of(String rawName, Location location) {
        return Bukkit.getBotManager().botCreator(rawName, location);
    }

    BotCreator name(String name);

    BotCreator skinName(String skinName);

    BotCreator skin(String[] skin);

    /**
     * Sets the skin of the bot using the Mojang API based on the provided skin name.
     * <p>
     * Need Async.
     *
     * @return BotCreator
     */
    BotCreator mojangAPISkin();

    BotCreator location(@NotNull Location location);

    BotCreator creator(@Nullable CommandSender creator);

    /**
     * Create a bot directly
     *
     * @return a bot, null spawn fail
     */
    @Nullable Bot spawn();

    /**
     * Create a bot and apply skin of player names `skinName` from MojangAPI
     * just like `mojangAPISkin().spawn()`, but async
     * <p>
     * you can not get the bot instance instantly because get skin in on async thread
     *
     * @param consumer Consumer
     */
    void spawnWithSkin(Consumer<Bot> consumer);
}
