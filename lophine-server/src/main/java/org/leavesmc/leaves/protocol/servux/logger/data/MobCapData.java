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

package org.leavesmc.leaves.protocol.servux.logger.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.entity.MobCategory;

import java.util.List;

public record MobCapData(List<Cap> data, long worldTick) {
    public static final int CAP_COUNT = MobCategory.values().length;

    private static MobCapData of(int count, List<Cap> data, long worldTick) {
        return new MobCapData(data, worldTick);
    }

    public static final Codec<MobCapData> CODEC = RecordCodecBuilder.create(
            (inst) -> inst.group(
                    PrimitiveCodec.INT.fieldOf("cap_count").forGetter($ -> CAP_COUNT),
                    Codec.list(Cap.CODEC).fieldOf("cap_data").forGetter(MobCapData::data),
                    PrimitiveCodec.LONG.fieldOf("WorldTick").forGetter(MobCapData::worldTick)
            ).apply(inst, MobCapData::of)
    );

    public record Cap(int current, int cap) {
        public static Codec<Cap> CODEC = RecordCodecBuilder.create(
                (inst) -> inst.group(
                        PrimitiveCodec.INT.fieldOf("current").forGetter(Cap::current),
                        PrimitiveCodec.INT.fieldOf("cap").forGetter(Cap::cap)
                ).apply(inst, Cap::new)
        );
    }
}