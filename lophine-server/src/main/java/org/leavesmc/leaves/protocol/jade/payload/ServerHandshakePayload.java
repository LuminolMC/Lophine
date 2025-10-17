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

package org.leavesmc.leaves.protocol.jade.payload;


import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.leavesmc.leaves.protocol.core.LeavesCustomPayload;
import org.leavesmc.leaves.protocol.jade.JadeProtocol;

import java.util.List;
import java.util.Map;

import static org.leavesmc.leaves.protocol.jade.util.JadeCodec.PRIMITIVE_STREAM_CODEC;

public record ServerHandshakePayload(
        Map<ResourceLocation, Object> serverConfig,
        List<Block> shearableBlocks,
        List<ResourceLocation> blockProviderIds,
        List<ResourceLocation> entityProviderIds
) implements LeavesCustomPayload {

    @ID
    private static final ResourceLocation PACKET_SERVER_HANDSHAKE = JadeProtocol.id("server_handshake");

    @Codec
    private static final StreamCodec<RegistryFriendlyByteBuf, ServerHandshakePayload> CODEC = StreamCodec.composite(
            ByteBufCodecs.map(Maps::newHashMapWithExpectedSize, ResourceLocation.STREAM_CODEC, PRIMITIVE_STREAM_CODEC),
            ServerHandshakePayload::serverConfig,
            ByteBufCodecs.registry(Registries.BLOCK).apply(ByteBufCodecs.list()),
            ServerHandshakePayload::shearableBlocks,
            ByteBufCodecs.<ByteBuf, ResourceLocation>list().apply(ResourceLocation.STREAM_CODEC),
            ServerHandshakePayload::blockProviderIds,
            ByteBufCodecs.<ByteBuf, ResourceLocation>list().apply(ResourceLocation.STREAM_CODEC),
            ServerHandshakePayload::entityProviderIds,
            ServerHandshakePayload::new
    );
}