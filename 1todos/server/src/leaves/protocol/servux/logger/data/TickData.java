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

public record TickData(double mspt, double tps, long sprintTicks, boolean frozen, boolean sprinting, boolean stepping) {
    public static Codec<TickData> CODEC = RecordCodecBuilder.create(
            (inst) -> inst.group(
                    PrimitiveCodec.DOUBLE.fieldOf("mspt").forGetter(TickData::mspt),
                    PrimitiveCodec.DOUBLE.fieldOf("tps").forGetter(TickData::tps),
                    PrimitiveCodec.LONG.fieldOf("sprintTicks").forGetter(TickData::sprintTicks),
                    PrimitiveCodec.BOOL.fieldOf("frozen").forGetter(TickData::frozen),
                    PrimitiveCodec.BOOL.fieldOf("sprinting").forGetter(TickData::sprinting),
                    PrimitiveCodec.BOOL.fieldOf("stepping").forGetter(TickData::stepping)
            ).apply(inst, TickData::new)
    );
}