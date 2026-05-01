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

import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.entity.bot.Bot;

/**
 * Represents a fakeplayer related event
 */
public abstract class BotEvent extends Event {

    protected Bot bot;

    public BotEvent(@NotNull final Bot who) {
        bot = who;
    }

    public BotEvent(@NotNull final Bot who, boolean async) {
        super(async);
        bot = who;
    }

    /**
     * Returns the fakeplayer involved in this event
     *
     * @return Fakeplayer who is involved in this event
     */
    @NotNull
    public final Bot getBot() {
        return bot;
    }
}
