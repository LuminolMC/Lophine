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

package org.leavesmc.leaves.protocol.servux.litematics.utils;

import net.minecraft.core.Vec3i;

public record IntBoundingBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {

    public boolean containsPos(Vec3i pos) {
        return pos.getX() >= this.minX && pos.getX() <= this.maxX && pos.getZ() >= this.minZ && pos.getZ() <= this.maxZ && pos.getY() >= this.minY && pos.getY() <= this.maxY;
    }

    public boolean intersects(IntBoundingBox box) {
        return this.maxX >= box.minX && this.minX <= box.maxX && this.maxZ >= box.minZ && this.minZ <= box.maxZ && this.maxY >= box.minY && this.minY <= box.maxY;
    }

    public IntBoundingBox expand(int amount) {
        return this.expand(amount, amount, amount);
    }

    public IntBoundingBox expand(int x, int y, int z) {
        return new IntBoundingBox(this.minX - x, this.minY - y, this.minZ - z, this.maxX + x, this.maxY + y, this.maxZ + z);
    }

    public IntBoundingBox shrink(int x, int y, int z) {
        return this.expand(-x, -y, -z);
    }
}
