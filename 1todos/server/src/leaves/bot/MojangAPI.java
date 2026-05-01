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

package org.leavesmc.leaves.bot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MojangAPI {

    private static final Map<String, String[]> CACHE = new HashMap<>();

    public static String[] getSkin(String name) {
        if (FakeplayerConfig.useSkinCache && CACHE.containsKey(name)) {
            return CACHE.get(name);
        }

        String[] values = pullFromAPI(name);
        CACHE.put(name, values);
        return values;
    }

    // Laggggggggggggggggggggggggggggggggggggggggg
    public static String[] pullFromAPI(String name) {
        try {
            String uuid = JsonParser.parseReader(new InputStreamReader(URI.create("https://api.mojang.com/users/profiles/minecraft/" + name).toURL().openStream()))
                    .getAsJsonObject().get("id").getAsString();
            JsonObject property = JsonParser.parseReader(new InputStreamReader(URI.create("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false").toURL().openStream()))
                    .getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
            return new String[]{property.get("value").getAsString(), property.get("signature").getAsString()};
        } catch (IOException | IllegalStateException | IllegalArgumentException e) {
            return null;
        }
    }
}
