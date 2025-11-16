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

package org.leavesmc.leaves.replay;

import com.google.gson.*;

import java.lang.reflect.Type;

public class ReplayMarker {

    public int time;
    public String name;
    public double x = 0;
    public double y = 0;
    public double z = 0;
    public float phi = 0;
    public float theta = 0;
    public float varphi = 0;

    public static class Serializer implements JsonSerializer<ReplayMarker> {
        @Override
        public JsonElement serialize(ReplayMarker src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject ret = new JsonObject();
            JsonObject value = new JsonObject();
            JsonObject position = new JsonObject();
            ret.add("realTimestamp", new JsonPrimitive(src.time));
            ret.add("value", value);

            value.add("name", new JsonPrimitive(src.name));
            value.add("position", position);

            position.add("x", new JsonPrimitive(src.x));
            position.add("y", new JsonPrimitive(src.y));
            position.add("z", new JsonPrimitive(src.z));
            position.add("yaw", new JsonPrimitive(src.phi));
            position.add("pitch", new JsonPrimitive(src.theta));
            position.add("roll", new JsonPrimitive(src.varphi));
            return ret;
        }
    }
}
