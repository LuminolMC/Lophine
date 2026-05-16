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

package org.leavesmc.leaves.event.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UpdateSuppressionEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final @Nullable Player player;
    private final @Nullable Location position;
    private final @Nullable Material material;
    private final @NotNull Throwable throwable;

    public UpdateSuppressionEvent(@Nullable Player player, @Nullable Location position, @Nullable Material material, @NotNull Throwable throwable) {
        this.player = player;
        this.position = position;
        this.material = material;
        this.throwable = throwable;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public @Nullable Player getPlayer() {
        return player;
    }

    public @NotNull Throwable getThrowable() {
        return throwable;
    }

    public @Nullable Location getPosition() {
        return position;
    }

    public @Nullable Material getMaterial() {
        return material;
    }
}
