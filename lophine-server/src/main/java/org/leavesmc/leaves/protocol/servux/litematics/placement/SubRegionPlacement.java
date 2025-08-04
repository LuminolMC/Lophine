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

package org.leavesmc.leaves.protocol.servux.litematics.placement;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import org.leavesmc.leaves.protocol.servux.ServuxProtocol;

public record SubRegionPlacement(
        String name,
        BlockPos pos,
        Rotation rotation,
        Mirror mirror,
        boolean enabled,
        boolean ignoreEntities
) {
    public SubRegionPlacement(String name, BlockPos pos) {
        this(name, pos, Rotation.NONE, Mirror.NONE, true, false);
    }

    public boolean matchesRequirement(RequiredEnabled required) {
        if (required == RequiredEnabled.ANY) {
            return true;
        }

        if (required == RequiredEnabled.PLACEMENT_ENABLED) {
            return this.enabled();
        }

        ServuxProtocol.LOGGER.warn("RequiredEnabled.RENDERING_ENABLED is not supported on server side!");
        return false;
    }

    public enum RequiredEnabled {
        ANY,
        PLACEMENT_ENABLED,
        RENDERING_ENABLED
    }
}