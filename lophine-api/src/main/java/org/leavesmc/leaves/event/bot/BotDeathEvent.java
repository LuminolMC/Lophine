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
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.entity.bot.Bot;

public class BotDeathEvent extends BotEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean cancel = false;
    private boolean sendDeathMessage;
    private Component deathMessage;

    public BotDeathEvent(@NotNull Bot who, @Nullable Component deathMessage, boolean sendDeathMessage) {
        super(who);
        this.deathMessage = deathMessage;
        this.sendDeathMessage = sendDeathMessage;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public Component deathMessage() {
        return deathMessage;
    }

    public void deathMessage(Component deathMessage) {
        this.deathMessage = deathMessage;
    }

    @Nullable
    public String getDeathMessage() {
        return this.deathMessage == null ? null : LegacyComponentSerializer.legacySection().serialize(this.deathMessage);
    }

    public void setDeathMessage(@Nullable String deathMessage) {
        this.deathMessage = deathMessage != null ? LegacyComponentSerializer.legacySection().deserialize(deathMessage) : null;
    }

    public boolean isSendDeathMessage() {
        return sendDeathMessage;
    }

    public void setSendDeathMessage(boolean sendDeathMessage) {
        this.sendDeathMessage = sendDeathMessage;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }
}
