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

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.protocol.syncmatica.exchange.ExchangeTarget;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerIdentifierProvider {

    private final Map<UUID, PlayerIdentifier> identifiers = new HashMap<>();

    public PlayerIdentifierProvider() {
        identifiers.put(PlayerIdentifier.MISSING_PLAYER_UUID, PlayerIdentifier.MISSING_PLAYER);
    }

    public PlayerIdentifier createOrGet(final ExchangeTarget exchangeTarget) {
        return createOrGet(CommunicationManager.getGameProfile(exchangeTarget));
    }

    public PlayerIdentifier createOrGet(final @NotNull GameProfile gameProfile) {
        return createOrGet(gameProfile.getId(), gameProfile.getName());
    }

    public PlayerIdentifier createOrGet(final UUID uuid, final String playerName) {
        return identifiers.computeIfAbsent(uuid, id -> new PlayerIdentifier(uuid, playerName));
    }

    public void updateName(final UUID uuid, final String playerName) {
        createOrGet(uuid, playerName).updatePlayerName(playerName);
    }

    public PlayerIdentifier fromJson(final @NotNull JsonObject obj) {
        if (!obj.has("uuid") || !obj.has("name")) {
            return PlayerIdentifier.MISSING_PLAYER;
        }

        final UUID jsonUUID = UUID.fromString(obj.get("uuid").getAsString());
        return identifiers.computeIfAbsent(jsonUUID,
                key -> new PlayerIdentifier(jsonUUID, obj.get("name").getAsString())
        );
    }
}
