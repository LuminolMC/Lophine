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

import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SyncmaticManager {

    public static final String PLACEMENTS_JSON_KEY = "placements";
    private final Map<UUID, ServerPlacement> schematics = new HashMap<>();

    public void addPlacement(final ServerPlacement placement) {
        schematics.put(placement.getId(), placement);
        updateServerPlacement();
    }

    public ServerPlacement getPlacement(final UUID id) {
        return schematics.get(id);
    }

    public Collection<ServerPlacement> getAll() {
        return schematics.values();
    }

    public void removePlacement(final @NotNull ServerPlacement placement) {
        schematics.remove(placement.getId());
        updateServerPlacement();
    }

    public void updateServerPlacement() {
        saveServer();
    }

    public void startup() {
        loadServer();
    }

    private void saveServer() {
        final JsonObject obj = new JsonObject();
        final JsonArray arr = new JsonArray();

        for (final ServerPlacement p : getAll()) {
            arr.add(p.toJson());
        }

        obj.add(PLACEMENTS_JSON_KEY, arr);
        final File backup = new File(SyncmaticaProtocol.getLitematicFolder(), "placements.json.bak");
        final File incoming = new File(SyncmaticaProtocol.getLitematicFolder(), "placements.json.new");
        final File current = new File(SyncmaticaProtocol.getLitematicFolder(), "placements.json");

        try (final FileWriter writer = new FileWriter(incoming)) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(obj));
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }

        SyncmaticaProtocol.backupAndReplace(backup.toPath(), current.toPath(), incoming.toPath());
    }

    private void loadServer() {
        final File f = new File(SyncmaticaProtocol.getLitematicFolder(), "placements.json");
        if (f.exists() && f.isFile() && f.canRead()) {
            JsonElement element = null;
            try {
                final JsonParser parser = new JsonParser();
                final FileReader reader = new FileReader(f);

                element = parser.parse(reader);
                reader.close();

            } catch (final Exception e) {
                e.printStackTrace();
            }
            if (element == null) {
                return;
            }
            try {
                final JsonObject obj = element.getAsJsonObject();
                if (obj == null || !obj.has(PLACEMENTS_JSON_KEY)) {
                    return;
                }
                final JsonArray arr = obj.getAsJsonArray(PLACEMENTS_JSON_KEY);
                for (final JsonElement elem : arr) {
                    final ServerPlacement placement = ServerPlacement.fromJson(elem.getAsJsonObject());
                    if (placement != null) {
                        schematics.put(placement.getId(), placement);
                    }
                }

            } catch (final IllegalStateException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
