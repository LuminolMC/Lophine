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

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.entity.bot.Bot;

/**
 * Called when a fakeplayer joins a server
 */
public class BotJoinEvent extends BotEvent {

    private static final HandlerList handlers = new HandlerList();

    private Component joinMessage;

    public BotJoinEvent(@NotNull final Bot who, @Nullable final Component joinMessage) {
        super(who);
        this.joinMessage = joinMessage;
    }

    public BotJoinEvent(@NotNull final Bot who, @Nullable final String joinMessage) {
        super(who);
        this.joinMessage = joinMessage != null ? LegacyComponentSerializer.legacySection().deserialize(joinMessage) : null;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    public void joinMessage(@Nullable final Component joinMessage) {
        this.joinMessage = joinMessage;
    }

    @Nullable
    public Component joinMessage() {
        return joinMessage;
    }

    /**
     * Gets the join message to send to all online players
     *
     * @return string join message. Can be null
     */
    @Nullable
    public String getJoinMessage() {
        return this.joinMessage == null ? null : LegacyComponentSerializer.legacySection().serialize(this.joinMessage);
    }

    /**
     * Sets the join message to send to all online players
     *
     * @param joinMessage join message. If null, no message will be sent
     */
    public void setJoinMessage(@Nullable String joinMessage) {
        this.joinMessage = joinMessage != null ? LegacyComponentSerializer.legacySection().deserialize(joinMessage) : null;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return handlers;
    }
}
