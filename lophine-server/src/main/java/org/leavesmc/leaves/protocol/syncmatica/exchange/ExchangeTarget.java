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

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.leavesmc.leaves.protocol.core.ProtocolUtils;
import org.leavesmc.leaves.protocol.syncmatica.FeatureSet;
import org.leavesmc.leaves.protocol.syncmatica.SyncmaticaPayload;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExchangeTarget {

    private final List<Exchange> ongoingExchanges = new ArrayList<>();
    private final ServerGamePacketListenerImpl client;
    private FeatureSet features;

    public ExchangeTarget(final ServerGamePacketListenerImpl client) {
        this.client = client;
    }

    public void sendPacket(final ResourceLocation id, final FriendlyByteBuf packetBuf) {
        ProtocolUtils.sendPayloadPacket(client.player, new SyncmaticaPayload(id, packetBuf));
    }

    public FeatureSet getFeatureSet() {
        return features;
    }

    public void setFeatureSet(final FeatureSet f) {
        features = f;
    }

    public Collection<Exchange> getExchanges() {
        return ongoingExchanges;
    }
}
