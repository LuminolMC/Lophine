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

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import org.leavesmc.leaves.protocol.core.LeavesCustomPayload;

public record SyncmaticaPayload(ResourceLocation packetType, FriendlyByteBuf data) implements LeavesCustomPayload {

    @ID
    private static final ResourceLocation NETWORK_ID = ResourceLocation.tryBuild(SyncmaticaProtocol.PROTOCOL_ID, "main");

    @Codec
    private static final StreamCodec<FriendlyByteBuf, SyncmaticaPayload> CODEC = StreamCodec.of(
            (buf, payload) -> buf.writeResourceLocation(payload.packetType()).writeBytes(payload.data()),
            buf -> new SyncmaticaPayload(buf.readResourceLocation(), new FriendlyByteBuf(buf.readBytes(buf.readableBytes())))
    );
}
