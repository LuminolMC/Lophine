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

public enum LocalLitematicState {
    NO_LOCAL_LITEMATIC(true, false),
    LOCAL_LITEMATIC_DESYNC(true, false),
    DOWNLOADING_LITEMATIC(false, false),
    LOCAL_LITEMATIC_PRESENT(false, true);

    private final boolean downloadReady;
    private final boolean fileReady;

    LocalLitematicState(final boolean downloadReady, final boolean fileReady) {
        this.downloadReady = downloadReady;
        this.fileReady = fileReady;
    }

    public boolean isReadyForDownload() {
        return downloadReady;
    }

    public boolean isLocalFileReady() {
        return fileReady;
    }
}
