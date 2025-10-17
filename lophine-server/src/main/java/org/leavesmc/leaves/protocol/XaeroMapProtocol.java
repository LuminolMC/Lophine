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
