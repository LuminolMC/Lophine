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

import net.minecraft.resources.ResourceLocation;

public enum PacketType {
    REGISTER_METADATA("register_metadata"),
    CANCEL_SHARE("cancel_share"),
    REQUEST_LITEMATIC("request_download"),
    SEND_LITEMATIC("send_litematic"),
    RECEIVED_LITEMATIC("received_litematic"),
    FINISHED_LITEMATIC("finished_litematic"),
    CANCEL_LITEMATIC("cancel_litematic"),
    REMOVE_SYNCMATIC("remove_syncmatic"),
    REGISTER_VERSION("register_version"),
    CONFIRM_USER("confirm_user"),
    FEATURE_REQUEST("feature_request"),
    FEATURE("feature"),
    MODIFY("modify"),
    MODIFY_REQUEST("modify_request"),
    MODIFY_REQUEST_DENY("modify_request_deny"),
    MODIFY_REQUEST_ACCEPT("modify_request_accept"),
    MODIFY_FINISH("modify_finish"),
    MESSAGE("mesage");

    public final ResourceLocation identifier;

    PacketType(final String id) {
        identifier = ResourceLocation.tryBuild(SyncmaticaProtocol.PROTOCOL_ID, id);
    }
}
