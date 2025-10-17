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

package org.leavesmc.leaves.protocol.syncmatica.exchange;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.protocol.syncmatica.*;

import java.util.Collection;

public class VersionHandshakeServer extends FeatureExchange {

    public VersionHandshakeServer(final ExchangeTarget partner) {
        super(partner);
    }

    @Override
    public boolean checkPacket(final @NotNull ResourceLocation id, final FriendlyByteBuf packetBuf) {
        return id.equals(PacketType.REGISTER_VERSION.identifier)
                || super.checkPacket(id, packetBuf);
    }

    @Override
    public void handle(final @NotNull ResourceLocation id, final FriendlyByteBuf packetBuf) {
        if (id.equals(PacketType.REGISTER_VERSION.identifier)) {
            String partnerVersion = packetBuf.readUtf();
            if (partnerVersion.equals("0.0.1")) {
                close(false);
                return;
            }
            final FeatureSet fs = FeatureSet.fromVersionString(partnerVersion);
            if (fs == null) {
                requestFeatureSet();
            } else {
                getPartner().setFeatureSet(fs);
                onFeatureSetReceive();
            }
        } else {
            super.handle(id, packetBuf);
        }

    }

    @Override
    public void onFeatureSetReceive() {
        final FriendlyByteBuf newBuf = new FriendlyByteBuf(Unpooled.buffer());
        final Collection<ServerPlacement> l = SyncmaticaProtocol.getSyncmaticManager().getAll();
        newBuf.writeInt(l.size());
        for (final ServerPlacement p : l) {
            CommunicationManager.putMetaData(p, newBuf, getPartner());
        }
        getPartner().sendPacket(PacketType.CONFIRM_USER.identifier, newBuf);
        succeed();
    }

    @Override
    public void init() {
        final FriendlyByteBuf newBuf = new FriendlyByteBuf(Unpooled.buffer());
        newBuf.writeUtf(SyncmaticaProtocol.PROTOCOL_VERSION);
        getPartner().sendPacket(PacketType.REGISTER_VERSION.identifier, newBuf);
    }
}
