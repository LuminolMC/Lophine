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

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import net.minecraft.nbt.CompoundTag;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.command.CommandContext;

public class TickTypeConfig extends AbstractBotConfig<ServerBot.TickType, String, TickTypeConfig> {
    private ServerBot.TickType value;

    public TickTypeConfig() {
        super("tick_type", StringArgumentType.word(), TickTypeConfig::new);
        this.value = FakeplayerConfig.tickType;
    }

    @Override
    public void applySuggestions(CommandContext context, @NotNull SuggestionsBuilder builder) {
        builder.suggest("network");
        builder.suggest("entity_list");
    }

    @Override
    public ServerBot.TickType loadFromCommand(@NotNull CommandContext context) throws CommandSyntaxException {
        String raw = context.getString(getName());
        return switch (raw) {
            case "network" -> ServerBot.TickType.NETWORK;
            case "entity_list" -> ServerBot.TickType.ENTITY_LIST;
            default -> throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().create();
        };
    }

    @Override
    public ServerBot.TickType getValue() {
        return value;
    }

    @Override
    public void setValue(ServerBot.TickType value) throws IllegalArgumentException {
        this.value = value;
    }

    @Override
    @NotNull
    public CompoundTag save(@NotNull CompoundTag nbt) {
        super.save(nbt);
        nbt.putString(getName(), this.getValue().toString());
        return nbt;
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        String raw = nbt.getStringOr(getName(), FakeplayerConfig.tickType.name());
        this.setValue(switch (raw) {
            case "network" -> ServerBot.TickType.NETWORK;
            case "entity_list" -> ServerBot.TickType.ENTITY_LIST;
            default -> throw new IllegalStateException("Unexpected bot tick type value: " + raw);
        });
    }
}
