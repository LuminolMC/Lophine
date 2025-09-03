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

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.command.CommandContext;

import java.util.function.Supplier;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static org.leavesmc.leaves.command.ArgumentNode.ArgumentSuggestions.strings;

public abstract class AbstractTimerBotAction<E extends AbstractTimerBotAction<E>> extends AbstractBotAction<E> {

    public AbstractTimerBotAction(String name, Supplier<E> creator) {
        super(name, creator);
        this.addArgument("delay", integer(0))
                .suggests(strings("0", "5", "10", "20"))
                .setOptional(true);
        this.addArgument("interval", integer(0))
                .suggests(strings("20", "0", "5", "10"))
                .setOptional(true);
        this.addArgument("do_number", integer(-1))
                .suggests(((context, builder) -> builder.suggest("-1", Component.literal("do infinite times"))))
                .setOptional(true);
    }

    @Override
    public void loadCommand(@NotNull CommandContext context) {
        this.setStartDelayTick(context.getIntegerOrDefault("delay", 0));
        this.setDoIntervalTick(context.getIntegerOrDefault("interval", 20));
        this.setDoNumber(context.getIntegerOrDefault("do_number", 1));
    }

    @Override
    public void provideActionData(@NotNull ActionData data) {
        super.provideActionData(data);
        data.add("delay", String.valueOf(this.getStartDelayTick()));
        data.add("interval", String.valueOf(this.getDoIntervalTick()));
        data.add("do_number", String.valueOf(this.getDoNumber()));
        data.add("remaining_do_number", String.valueOf(this.getDoNumberRemaining()));
    }
}
