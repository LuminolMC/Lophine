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

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class RecordMetaData {

    public static final int CURRENT_FILE_FORMAT_VERSION = 14;

    public boolean singleplayer = false;
    public String serverName = "Lophine";
    public int duration = 0;
    public long date;
    public String mcversion;
    public String fileFormat = "MCPR";
    public int fileFormatVersion;
    public int protocol;
    public String generator;
    public int selfId = -1;

    public Set<UUID> players = new HashSet<>();

    public RecordMetaData copy() {
        RecordMetaData ret = new RecordMetaData();
        synchronized (this) {
            ret.singleplayer = this.singleplayer;
            ret.serverName = this.serverName;
            ret.duration = this.duration;
            ret.date = this.date;
            ret.mcversion = this.mcversion;
            ret.fileFormat = this.fileFormat;
            ret.fileFormatVersion = this.fileFormatVersion;
            ret.protocol = this.protocol;
            ret.generator = this.generator;
            ret.selfId = this.selfId;
            ret.players = new HashSet<>(this.players);
        }
        return ret;
    }
}
