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

package org.leavesmc.leaves.protocol;

import fun.bm.lophine.config.modules.function.protocol.XaeroMapProtocolConfig;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.protocol.core.LeavesProtocol;
import org.leavesmc.leaves.protocol.core.ProtocolUtils;

@LeavesProtocol.Register(namespace = "xaerominimap_or_xaeroworldmap_i_dont_care")
public class XaeroMapProtocol implements LeavesProtocol {

    public static final String PROTOCOL_ID_MINI = "xaerominimap";
    public static final String PROTOCOL_ID_WORLD = "xaeroworldmap";

    private static final ResourceLocation MINIMAP_KEY = idMini("main");
    private static final ResourceLocation WORLDMAP_KEY = idWorld("main");

    @Contract("_ -> new")
    public static ResourceLocation idMini(String path) {
        return ResourceLocation.tryBuild(PROTOCOL_ID_MINI, path);
    }

    @Contract("_ -> new")
    public static ResourceLocation idWorld(String path) {
        return ResourceLocation.tryBuild(PROTOCOL_ID_WORLD, path);
    }

    public static void onSendWorldInfo(@NotNull ServerPlayer player) {
        if (XaeroMapProtocolConfig.enabled) {
            ProtocolUtils.sendBytebufPacket(player, MINIMAP_KEY, buf -> {
                buf.writeByte(0);
                buf.writeInt(XaeroMapProtocolConfig.xaeroMapServerID);
            });
            ProtocolUtils.sendBytebufPacket(player, WORLDMAP_KEY, buf -> {
                buf.writeByte(0);
                buf.writeInt(XaeroMapProtocolConfig.xaeroMapServerID);
            });
        }
    }

    @Override
    public boolean isActive() {
        return XaeroMapProtocolConfig.enabled;
    }
}
