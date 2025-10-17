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

import java.util.UUID;

public class ModifyExchangeServer extends AbstractExchange {

    private final ServerPlacement placement;
    UUID placementId;

    public ModifyExchangeServer(final UUID placeId, final ExchangeTarget partner) {
        super(partner);
        placementId = placeId;
        placement = SyncmaticaProtocol.getSyncmaticManager().getPlacement(placementId);
    }

    @Override
    public boolean checkPacket(final @NotNull ResourceLocation id, final FriendlyByteBuf packetBuf) {
        return id.equals(PacketType.MODIFY_FINISH.identifier) && checkUUID(packetBuf, placement.getId());
    }

    @Override
    public void handle(final @NotNull ResourceLocation id, final @NotNull FriendlyByteBuf packetBuf) {
        packetBuf.readUUID();
        if (id.equals(PacketType.MODIFY_FINISH.identifier)) {
            CommunicationManager.receivePositionData(placement, packetBuf, getPartner());
            final PlayerIdentifier identifier = SyncmaticaProtocol.getPlayerIdentifierProvider().createOrGet(
                    getPartner()
            );
            placement.setLastModifiedBy(identifier);
            SyncmaticaProtocol.getSyncmaticManager().updateServerPlacement();
            succeed();
        }
    }

    @Override
    public void init() {
        if (getPlacement() == null || CommunicationManager.getModifier(placement) != null) {
            close(true);
        } else {
            if (SyncmaticaProtocol.getPlayerIdentifierProvider().createOrGet(this.getPartner()).uuid.equals(placement.getOwner().uuid)) {
                accept();
            } else {
                close(true);
            }
        }
    }

    private void accept() {
        final FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeUUID(placement.getId());
        getPartner().sendPacket(PacketType.MODIFY_REQUEST_ACCEPT.identifier, buf);
        CommunicationManager.setModifier(placement, this);
    }

    @Override
    protected void sendCancelPacket() {
        final FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.buffer());
        buf.writeUUID(placementId);
        getPartner().sendPacket(PacketType.MODIFY_REQUEST_DENY.identifier, buf);
    }

    public ServerPlacement getPlacement() {
        return placement;
    }

    @Override
    protected void onClose() {
        if (CommunicationManager.getModifier(placement) == this) {
            CommunicationManager.setModifier(placement, null);
        }
    }
}
