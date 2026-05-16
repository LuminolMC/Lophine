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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class FileStorage {

    private final HashMap<ServerPlacement, Long> buffer = new HashMap<>();

    public LocalLitematicState getLocalState(final ServerPlacement placement) {
        final File localFile = getSchematicPath(placement);
        if (localFile.isFile()) {
            if (isDownloading(placement)) {
                return LocalLitematicState.DOWNLOADING_LITEMATIC;
            }
            if ((buffer.containsKey(placement) && buffer.get(placement) == localFile.lastModified()) || hashCompare(localFile, placement)) {
                return LocalLitematicState.LOCAL_LITEMATIC_PRESENT;
            }
            return LocalLitematicState.LOCAL_LITEMATIC_DESYNC;
        }
        return LocalLitematicState.NO_LOCAL_LITEMATIC;
    }

    private boolean isDownloading(final ServerPlacement placement) {
        return CommunicationManager.getDownloadState(placement);
    }

    public File getLocalLitematic(final ServerPlacement placement) {
        if (getLocalState(placement).isLocalFileReady()) {
            return getSchematicPath(placement);
        } else {
            return null;
        }
    }

    public File createLocalLitematic(final ServerPlacement placement) {
        if (getLocalState(placement).isLocalFileReady()) {
            throw new IllegalArgumentException("");
        }
        final File file = getSchematicPath(placement);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private boolean hashCompare(final File localFile, final ServerPlacement placement) {
        UUID hash = null;
        try {
            hash = SyncmaticaProtocol.createChecksum(new FileInputStream(localFile));
        } catch (final Exception e) {
            e.printStackTrace();
        }

        if (hash == null) {
            return false;
        }
        if (hash.equals(placement.getHash())) {
            buffer.put(placement, localFile.lastModified());
            return true;
        }
        return false;
    }

    @Contract("_ -> new")
    private @NotNull File getSchematicPath(final @NotNull ServerPlacement placement) {
        return new File(SyncmaticaProtocol.getLitematicFolder(), placement.getHash().toString() + ".litematic");
    }
}
