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

package org.leavesmc.leaves.bot.agent.configs;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.command.CommandContext;

import static net.minecraft.network.chat.Component.literal;

public class SimulationDistanceConfig extends AbstractBotConfig<Integer, Integer, SimulationDistanceConfig> {

    public SimulationDistanceConfig() {
        super("simulation_distance", IntegerArgumentType.integer(2, 32), SimulationDistanceConfig::new);
    }

    @Override
    public void applySuggestions(CommandContext context, @NotNull SuggestionsBuilder builder) {
        builder.suggest("2", literal("Minimum simulation distance"));
        builder.suggest("8");
        builder.suggest("12");
        builder.suggest("16");
        builder.suggest("32", literal("Maximum simulation distance"));
    }

    @Override
    public Integer getValue() {
        return this.bot.getBukkitEntity().getSimulationDistance();
    }

    @Override
    public void setValue(Integer value) {
        this.bot.getBukkitEntity().setSimulationDistance(value);
    }

    @Override
    public Integer loadFromCommand(@NotNull CommandContext context) {
        return context.getInteger(getName());
    }

    @Override
    @NotNull
    public CompoundTag save(@NotNull CompoundTag nbt) {
        super.save(nbt);
        nbt.putInt(getName(), this.getValue());
        return nbt;
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        this.setValue(nbt.getIntOr(getName(), FakeplayerConfig.getSimulationDistance(this.bot)));
    }
}
