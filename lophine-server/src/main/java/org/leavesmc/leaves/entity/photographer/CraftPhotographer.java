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

package org.leavesmc.leaves.entity.photographer;

import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.replay.ServerPhotographer;

import java.io.File;

public class CraftPhotographer extends CraftPlayer implements Photographer {

    public CraftPhotographer(CraftServer server, ServerPhotographer entity) {
        super(server, entity);
    }

    @Override
    public void stopRecording() {
        this.stopRecording(true);
    }

    @Override
    public void stopRecording(boolean async) {
        this.stopRecording(async, true);
    }

    @Override
    public void stopRecording(boolean async, boolean save) {
        this.getHandle().remove(async, save);
    }

    @Override
    public void pauseRecording() {
        this.getHandle().pauseRecording();
    }

    @Override
    public void resumeRecording() {
        this.getHandle().resumeRecording();
    }

    @Override
    public void setRecordFile(@NotNull File file) {
        this.getHandle().setSaveFile(file);
    }

    @Override
    public void setFollowPlayer(@Nullable Player player) {
        ServerPlayer serverPlayer = player != null ? ((CraftPlayer) player).getHandle() : null;
        this.getHandle().setFollowPlayer(serverPlayer);
    }

    @Override
    public @NotNull String getId() {
        return this.getHandle().createState.id;
    }

    @Override
    public ServerPhotographer getHandle() {
        return (ServerPhotographer) entity;
    }

    public void setHandle(final ServerPhotographer entity) {
        super.setHandle(entity);
    }

    @Override
    public String toString() {
        return "CraftPhotographer{" + "name=" + getName() + '}';
    }
}
