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

package org.leavesmc.leaves.command.bot.subcommands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.command.CommandContext;
import org.leavesmc.leaves.command.CustomArgumentNode;
import org.leavesmc.leaves.command.bot.BotSubcommand;
import org.leavesmc.leaves.command.bot.subcommands.action.ListCommand;
import org.leavesmc.leaves.command.bot.subcommands.action.StartCommand;
import org.leavesmc.leaves.command.bot.subcommands.action.StopCommand;

public class ActionCommand extends BotSubcommand {

    public ActionCommand() {
        super("action");
        children(BotArgument::new);
    }

    @Override
    public boolean requires(@NotNull CommandSourceStack source) {
        return FakeplayerConfig.canUseAction && super.requires(source);
    }

    public static class BotArgument extends CustomArgumentNode<ServerBot, String> {

        private BotArgument() {
            super("bot", new org.leavesmc.leaves.command.bot.BotArgument());
            children(
                    StartCommand::new,
                    StopCommand::new,
                    ListCommand::new
            );
        }

        public static @NotNull ServerBot getBot(@NotNull CommandContext context) throws CommandSyntaxException {
            return context.getCustomArgument(BotArgument.class);
        }
    }
}