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

package org.leavesmc.leaves.replay;

import net.kyori.adventure.text.Component;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.level.GameType;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ServerPhotographerGameMode extends ServerPlayerGameMode {

    public ServerPhotographerGameMode(ServerPhotographer photographer) {
        super(photographer);
        super.setGameModeForPlayer(GameType.SPECTATOR, null);
    }

    @Override
    public boolean changeGameModeForPlayer(@NotNull GameType gameMode) {
        return false;
    }

    @Nullable
    @Override
    public PlayerGameModeChangeEvent changeGameModeForPlayer(@NotNull GameType gameMode, PlayerGameModeChangeEvent.@NotNull Cause cause, @Nullable Component cancelMessage) {
        return null;
    }

    @Override
    protected void setGameModeForPlayer(@NotNull GameType gameMode, @Nullable GameType previousGameMode) {
    }

    @Override
    public void tick() {
    }
}
