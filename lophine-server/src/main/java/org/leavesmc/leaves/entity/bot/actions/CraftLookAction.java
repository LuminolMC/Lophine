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

package org.leavesmc.leaves.entity.bot.actions;

import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.leavesmc.leaves.bot.agent.actions.ServerLookAction;
import org.leavesmc.leaves.entity.bot.action.LookAction;

public class CraftLookAction extends CraftBotAction<LookAction, ServerLookAction> implements LookAction {

    public CraftLookAction(ServerLookAction serverAction) {
        super(serverAction, CraftLookAction::new);
    }

    @Override
    public LookAction setPos(Vector pos) {
        serverAction.setPos(pos);
        return this;
    }

    @Override
    public Vector getPos() {
        return serverAction.getPos();
    }

    @Override
    public LookAction setTarget(Player player) {
        serverAction.setTarget(((CraftPlayer) player).getHandle());
        return this;
    }

    @Override
    public Player getTarget() {
        return serverAction.getTarget().getBukkitEntity();
    }
}