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

package org.leavesmc.leaves.bot.agent.actions;

import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Vehicle;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.entity.bot.actions.CraftMountAction;

import java.util.Comparator;
import java.util.List;

public class ServerMountAction extends AbstractBotAction<ServerMountAction> {

    public ServerMountAction() {
        super("mount", ServerMountAction::new);
    }

    @Override
    public boolean doTick(@NotNull ServerBot bot) {
        Location center = bot.getBukkitEntity().getLocation();
        List<Vehicle> vehicles = center.getNearbyEntitiesByType(
                Vehicle.class,
                4,
                vehicle -> manhattanDistance(bot, ((CraftEntity) vehicle).getHandle()) <= 3
        ).stream().sorted(Comparator.comparingDouble(
                (vehicle) -> center.distanceSquared(vehicle.getLocation())
        )).toList();

        for (Vehicle vehicle : vehicles) {
            if (bot.startRiding(((CraftEntity) vehicle).getHandle(), false)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Object asCraft() {
        return new CraftMountAction(this);
    }

    private double manhattanDistance(@NotNull Entity entity1, @NotNull Entity entity2) {
        return Math.abs(entity1.getX() - entity2.getX()) +
                Math.abs(entity1.getY() - entity2.getY()) +
                Math.abs(entity1.getZ() - entity2.getZ());
    }
}
