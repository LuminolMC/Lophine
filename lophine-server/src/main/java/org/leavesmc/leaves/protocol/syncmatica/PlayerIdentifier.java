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
import com.google.gson.JsonPrimitive;

import java.util.UUID;

public class PlayerIdentifier {

    public static final UUID MISSING_PLAYER_UUID = UUID.fromString("4c1b738f-56fa-4011-8273-498c972424ea");
    public static final PlayerIdentifier MISSING_PLAYER = new PlayerIdentifier(MISSING_PLAYER_UUID, "No Player");

    public final UUID uuid;
    private String bufferedPlayerName;

    PlayerIdentifier(final UUID uuid, final String bufferedPlayerName) {
        this.uuid = uuid;
        this.bufferedPlayerName = bufferedPlayerName;
    }

    public String getName() {
        return bufferedPlayerName;
    }

    public void updatePlayerName(final String name) {
        bufferedPlayerName = name;
    }

    public JsonObject toJson() {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.add("uuid", new JsonPrimitive(uuid.toString()));
        jsonObject.add("name", new JsonPrimitive(bufferedPlayerName));

        return jsonObject;
    }
}
