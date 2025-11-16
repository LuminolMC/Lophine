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

package org.leavesmc.leaves.region;

import ca.spottedleaf.moonrise.paper.PaperHooks;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.chunk.LevelChunk;

public final class LeavesHooks extends PaperHooks {

    @Override
    public String getBrand() {
        return "Lophine"; // Lophine - rebrand
    }

    @Override
    public void onChunkWatch(ServerLevel world, LevelChunk chunk, ServerPlayer player) {
        super.onChunkWatch(world, chunk, player);
        org.leavesmc.leaves.protocol.servux.ServuxStructuresProtocol.onStartedWatchingChunk(player, chunk); // servux
    }
}
