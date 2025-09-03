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

import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.bot.ServerBot;
import org.leavesmc.leaves.bot.agent.actions.AbstractBotAction;
import org.leavesmc.leaves.entity.bot.action.BotAction;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class CraftBotAction<T extends BotAction<T>, S extends AbstractBotAction<S>> implements BotAction<T> {

    protected final S serverAction;
    protected final Function<S, T> creator;

    protected Consumer<T> onFail = null;
    protected Consumer<T> onSuccess = null;
    protected Consumer<T> onStop = null;

    public CraftBotAction(S serverAction, Function<S, T> creator) {
        this.serverAction = serverAction;
        this.creator = creator;
    }

    public AbstractBotAction<?> getHandle() {
        return serverAction;
    }

    @Override
    public String getName() {
        return serverAction.getName();
    }

    @Override
    public UUID getUUID() {
        return serverAction.getUUID();
    }

    public boolean doTick(@NotNull ServerBot bot) {
        return serverAction.doTick(bot);
    }

    @Override
    public void setOnFail(Consumer<T> onFail) {
        this.onFail = onFail;
        serverAction.setOnFail(it -> onFail.accept(creator.apply(it)));
    }

    @Override
    public Consumer<T> getOnFail() {
        return onFail;
    }

    @Override
    public void setOnSuccess(Consumer<T> onSuccess) {
        this.onSuccess = onSuccess;
        serverAction.setOnSuccess(it -> onSuccess.accept(creator.apply(it)));
    }

    @Override
    public Consumer<T> getOnSuccess() {
        return onSuccess;
    }

    @Override
    public void setOnStop(Consumer<T> onStop) {
        this.onStop = onStop;
        serverAction.setOnStop(it -> onStop.accept(creator.apply(it)));
    }

    @Override
    public Consumer<T> getOnStop() {
        return onStop;
    }

    @Override
    public void setCancelled(boolean cancel) {
        serverAction.setCancelled(cancel);
    }

    @Override
    public boolean isCancelled() {
        return serverAction.isCancelled();
    }
}
