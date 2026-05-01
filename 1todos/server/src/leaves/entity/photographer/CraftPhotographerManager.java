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

import com.google.common.collect.Lists;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.replay.BukkitRecorderOption;
import org.leavesmc.leaves.replay.RecorderOption;
import org.leavesmc.leaves.replay.ServerPhotographer;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

public class CraftPhotographerManager implements PhotographerManager {

    private final Collection<Photographer> photographerViews = Collections.unmodifiableList(Lists.transform(ServerPhotographer.getPhotographers(), ServerPhotographer::getBukkitPlayer));

    @Override
    public @Nullable Photographer getPhotographer(@NotNull UUID uuid) {
        ServerPhotographer photographer = ServerPhotographer.getPhotographer(uuid);
        if (photographer != null) {
            return photographer.getBukkitPlayer();
        }
        return null;
    }

    @Override
    public @Nullable Photographer getPhotographer(@NotNull String id) {
        ServerPhotographer photographer = ServerPhotographer.getPhotographer(id);
        if (photographer != null) {
            return photographer.getBukkitPlayer();
        }
        return null;
    }

    @Override
    public @Nullable Photographer createPhotographer(@NotNull String id, @NotNull Location location) {
        ServerPhotographer photographer = new ServerPhotographer.PhotographerCreateState(location, id, RecorderOption.createDefaultOption()).createSync();
        if (photographer != null) {
            return photographer.getBukkitPlayer();
        }
        return null;
    }

    @Override
    public @Nullable Photographer createPhotographer(@NotNull String id, @NotNull Location location, @NotNull BukkitRecorderOption recorderOption) {
        ServerPhotographer photographer = new ServerPhotographer.PhotographerCreateState(location, id, RecorderOption.createFromBukkit(recorderOption)).createSync();
        if (photographer != null) {
            return photographer.getBukkitPlayer();
        }
        return null;
    }

    @Override
    public void removePhotographer(@NotNull String id) {
        ServerPhotographer photographer = ServerPhotographer.getPhotographer(id);
        if (photographer != null) {
            photographer.remove(true);
        }
    }

    @Override
    public void removePhotographer(@NotNull UUID uuid) {
        ServerPhotographer photographer = ServerPhotographer.getPhotographer(uuid);
        if (photographer != null) {
            photographer.remove(true);
        }
    }

    @Override
    public void removeAllPhotographers() {
        for (ServerPhotographer photographer : ServerPhotographer.getPhotographers()) {
            photographer.remove(true);
        }
    }

    @Override
    public Collection<Photographer> getPhotographers() {
        return photographerViews;
    }
}
