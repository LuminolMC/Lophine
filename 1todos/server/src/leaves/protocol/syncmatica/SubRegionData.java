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

package org.leavesmc.leaves.protocol.syncmatica;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class SubRegionData {

    private boolean isModified;
    private Map<String, SubRegionPlacementModification> modificationData; // is null when isModified is false

    public SubRegionData() {
        this(false, null);
    }

    public SubRegionData(final boolean isModified, final Map<String, SubRegionPlacementModification> modificationData) {
        this.isModified = isModified;
        this.modificationData = modificationData;
    }

    @NotNull
    public static SubRegionData fromJson(final @NotNull JsonElement obj) {
        final SubRegionData newSubRegionData = new SubRegionData();

        newSubRegionData.isModified = true;

        for (final JsonElement modification : obj.getAsJsonArray()) {
            newSubRegionData.modify(SubRegionPlacementModification.fromJson(modification.getAsJsonObject()));
        }

        return newSubRegionData;
    }

    public void reset() {
        isModified = false;
        modificationData = null;
    }

    public void modify(final String name, final BlockPos position, final Rotation rotation, final Mirror mirror) {
        modify(new SubRegionPlacementModification(name, position, rotation, mirror));
    }

    public void modify(final SubRegionPlacementModification subRegionPlacementModification) {
        if (subRegionPlacementModification == null) {
            return;
        }
        isModified = true;
        if (modificationData == null) {
            modificationData = new HashMap<>();
        }
        modificationData.put(subRegionPlacementModification.name, subRegionPlacementModification);
    }

    public boolean isModified() {
        return isModified;
    }

    public Map<String, SubRegionPlacementModification> getModificationData() {
        return modificationData;
    }

    public JsonElement toJson() {
        return modificationDataToJson();
    }

    @NotNull
    private JsonElement modificationDataToJson() {
        final JsonArray arr = new JsonArray();

        for (final Map.Entry<String, SubRegionPlacementModification> entry : modificationData.entrySet()) {
            arr.add(entry.getValue().toJson());
        }

        return arr;
    }

    @Override
    public String toString() {
        if (!isModified) {
            return "[]";
        }
        return modificationData == null ? "[ERROR:null]" : modificationData.toString();
    }
}
