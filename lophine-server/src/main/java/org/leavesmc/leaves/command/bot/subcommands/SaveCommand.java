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
import net.kyori.adventure.text.format.NamedTextColor;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.bot.BotList;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.command.CommandContext;
import org.leavesmc.leaves.command.CustomArgumentNode;
import org.leavesmc.leaves.command.bot.BotSubcommand;
import org.leavesmc.leaves.event.bot.BotRemoveEvent;

import static io.papermc.paper.adventure.PaperAdventure.asAdventure;
import static net.kyori.adventure.text.Component.join;
import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.JoinConfiguration.spaces;

public class SaveCommand extends BotSubcommand {

    public SaveCommand() {
        super("save");
        children(BotArgument::new);
    }

    @Override
    public boolean requires(@NotNull CommandSourceStack source) {
        return FakeplayerConfig.canManualSaveAndLoad && super.requires(source);
    }

    private static class BotArgument extends CustomArgumentNode<ServerBot, String> {

        private BotArgument() {
            super("bot", new org.leavesmc.leaves.command.bot.BotArgument());
        }

        @Override
        protected boolean execute(@NotNull CommandContext context) throws CommandSyntaxException {
            save(context.getCustomArgument(BotArgument.class), context.getSender());
            return true;
        }

        private boolean save(ServerBot bot, CommandSender sender) {
            return save(bot, sender, true);
        }

        private boolean save(ServerBot bot, CommandSender sender, boolean taskQueue) {
            if (taskQueue) {
                bot.getBukkitEntity().taskScheduler.schedule((LivingEntity nmsEntity) -> saveOrigin(bot, sender), null, 1L);
            } else {
                saveOrigin(bot, sender);
            }
            return true;
        }

        private boolean saveOrigin(ServerBot bot, CommandSender sender) {
            BotList botList = BotList.INSTANCE;

            boolean success = botList.removeBot(bot, BotRemoveEvent.RemoveReason.COMMAND, sender, true);
            if (success) {
                sender.sendMessage(join(spaces(),
                        text("Successfully saved bot", NamedTextColor.GRAY),
                        asAdventure(bot.getDisplayName()),
                        text("as " + bot.createState.realName(), NamedTextColor.GRAY)
                ));
            } else {
                sender.sendMessage(text("Bot save canceled by a plugin", NamedTextColor.RED));
            }
            return success;
        }
    }
}
