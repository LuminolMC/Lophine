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

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecorderOption {

    public int recordDistance = -1;
    public String serverName = "Lophine";
    public RecordWeather forceWeather = null;
    public int forceDayTime = -1;
    public boolean ignoreChat = false;
    public boolean ignoreItem = false;

    @NotNull
    @Contract(" -> new")
    public static RecorderOption createDefaultOption() {
        return new RecorderOption();
    }

    @NotNull
    public static RecorderOption createFromBukkit(@NotNull BukkitRecorderOption bukkitRecorderOption) {
        RecorderOption recorderOption = new RecorderOption();
        // recorderOption.recordDistance = bukkitRecorderOption.recordDistance;
        // recorderOption.ignoreItem = bukkitRecorderOption.ignoreItem;
        recorderOption.serverName = bukkitRecorderOption.serverName;
        recorderOption.ignoreChat = bukkitRecorderOption.ignoreChat;
        recorderOption.forceDayTime = bukkitRecorderOption.forceDayTime;
        recorderOption.forceWeather = switch (bukkitRecorderOption.forceWeather) {
            case RAIN -> RecordWeather.RAIN;
            case CLEAR -> RecordWeather.CLEAR;
            case THUNDER -> RecordWeather.THUNDER;
            case NULL -> null;
        };
        return recorderOption;
    }

    public enum RecordWeather {
        CLEAR(new ClientboundGameEventPacket(ClientboundGameEventPacket.STOP_RAINING, 0), new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, 0), new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, 0)),
        RAIN(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_RAINING, 0), new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, 1), new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, 0)),
        THUNDER(new ClientboundGameEventPacket(ClientboundGameEventPacket.START_RAINING, 0), new ClientboundGameEventPacket(ClientboundGameEventPacket.RAIN_LEVEL_CHANGE, 1), new ClientboundGameEventPacket(ClientboundGameEventPacket.THUNDER_LEVEL_CHANGE, 1));

        private final List<Packet<?>> packets;

        RecordWeather(Packet<?>... packets) {
            this.packets = List.of(packets);
        }

        public List<Packet<?>> getPackets() {
            return packets;
        }
    }
}
