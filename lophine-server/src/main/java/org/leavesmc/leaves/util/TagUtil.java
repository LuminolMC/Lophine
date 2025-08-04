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

package org.leavesmc.leaves.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import org.jetbrains.annotations.Nullable;

public class TagUtil {

    public static CompoundTag saveEntity(@Nullable Entity entity) {
        if (entity == null) {
            return new CompoundTag();
        }
        TagValueOutput output = TagFactory.output();
        entity.save(output);
        return output.buildResult();
    }

    public static boolean saveEntity(Entity entity, CompoundTag tag) {
        if (entity == null) {
            return false;
        }
        TagValueOutput output = TagFactory.output(tag);
        return entity.save(output);
    }

    public static CompoundTag saveEntityWithoutId(Entity entity) {
        if (entity == null) {
            return new CompoundTag();
        }
        TagValueOutput output = TagFactory.output();
        entity.saveWithoutId(output);
        return output.buildResult();
    }

    public static CompoundTag saveTileWithId(@Nullable BlockEntity entity) {
        if (entity == null) {
            return new CompoundTag();
        }
        TagValueOutput output = TagFactory.output();
        entity.saveWithId(output);
        return output.buildResult();
    }

    public static boolean saveEntityAsPassenger(@Nullable Entity entity, CompoundTag tag) {
        if (entity == null) {
            return false;
        }
        TagValueOutput output = TagFactory.output(tag);
        return entity.saveAsPassenger(output);
    }

    public static void loadEntity(Entity entity, CompoundTag tag) {
        if (entity == null) {
            return;
        }
        TagValueInput input = TagFactory.input(tag);
        entity.load(input);
    }

    public static void loadTileWithComponents(BlockEntity entity, CompoundTag tag) {
        if (entity == null) {
            return;
        }
        TagValueInput input = TagFactory.input(tag);
        entity.loadWithComponents(input);
    }

}
