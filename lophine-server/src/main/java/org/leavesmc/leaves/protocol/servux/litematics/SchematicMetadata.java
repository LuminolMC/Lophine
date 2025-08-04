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

package org.leavesmc.leaves.protocol.servux.litematics;

import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.protocol.servux.litematics.utils.FileType;
import org.leavesmc.leaves.protocol.servux.litematics.utils.NbtUtils;
import org.leavesmc.leaves.protocol.servux.litematics.utils.Schema;

import javax.annotation.Nullable;
import java.util.Objects;

public record SchematicMetadata(
        String name,
        String author,
        String description,
        Vec3i enclosingSize,
        long timeCreated,
        long timeModified,
        int minecraftDataVersion,
        int schematicVersion,
        Schema schema,
        FileType type,
        int regionCount,
        long totalVolume,
        long totalBlocks,
        @Nullable int[] thumbnailPixelData
) {
    @NotNull
    @Contract("_, _, _, _ -> new")
    public static SchematicMetadata readFromNBT(@NotNull CompoundTag nbt, int version, int minecraftDataVersion, FileType fileType) {
        String name = nbt.getStringOr("Name", "?");
        String author = nbt.getStringOr("Author", "?");
        String description = nbt.getStringOr("Description", "");
        int regionCount = nbt.getIntOr("RegionCount", -1);
        long timeCreated = nbt.getLongOr("TimeCreated", -1L);
        long timeModified = nbt.getLongOr("TimeModified", -1L);
        long totalVolume = nbt.getIntOr("TotalVolume", -1);
        long totalBlocks = nbt.getIntOr("TotalBlocks", -1);
        Vec3i enclosingSize = Objects.requireNonNullElse(NbtUtils.readVec3iFromTag(nbt.getCompoundOrEmpty("EnclosingSize")), Vec3i.ZERO);
        int[] thumbnailPixelData = nbt.getIntArray("PreviewImageData").orElse(null);
        return new SchematicMetadata(name, author, description, enclosingSize, timeCreated, timeModified, minecraftDataVersion, version, Schema.getSchemaByDataVersion(minecraftDataVersion), fileType, regionCount, totalVolume, totalBlocks, thumbnailPixelData);
    }
}
