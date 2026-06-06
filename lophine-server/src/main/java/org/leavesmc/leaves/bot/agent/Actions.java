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

package org.leavesmc.leaves.bot.agent;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.bot.agent.actions.*;
import org.leavesmc.leaves.entity.bot.action.*;

import java.util.*;

public class Actions {

    private static final Map<String, AbstractBotAction<?>> actionsByName = new HashMap<>();
    private static final Map<Class<?>, AbstractBotAction<?>> actionsByClass = new HashMap<>();
    private static final Set<String> guiRegistered = new HashSet<>();

    static {
        register(new ServerAttackAction(), AttackAction.class);
        register(new ServerBreakBlockAction(), BreakBlockAction.class);
        register(new ServerDropAction(), DropAction.class);
        register(new ServerJumpAction(), JumpAction.class);
        register(new ServerSneakAction(), SneakAction.class);
        register(new ServerUseItemAutoAction(), UseItemAutoAction.class);
        register(new ServerUseItemAction(), UseItemAction.class, false);
        register(new ServerUseItemOnAction(), UseItemOnAction.class, false);
        register(new ServerUseItemToAction(), UseItemToAction.class, false);
        register(new ServerUseItemOffhandAction(), UseItemOffhandAction.class, false);
        register(new ServerUseItemOnOffhandAction(), UseItemOnOffhandAction.class, false);
        register(new ServerUseItemToOffhandAction(), UseItemToOffhandAction.class, false);
        register(new ServerLookAction(), LookAction.class);
        register(new ServerFishAction(), FishAction.class, false);
        register(new ServerSwimAction(), SwimAction.class);
        register(new ServerRotationAction(), RotationAction.class);
        register(new ServerMoveAction(), MoveAction.class);
        register(new ServerMountAction(), MountAction.class);
        register(new ServerSwapAction(), SwapAction.class);
    }

    public static boolean register(@NotNull AbstractBotAction<?> action, Class<?> type) {
        return register(action, type, true);
    }

    public static boolean register(@NotNull AbstractBotAction<?> action, Class<?> type, boolean registerToGui) {
        if (registerToGui) {
            guiRegistered.add(action.getName());
        }
        if (!actionsByName.containsKey(action.getName())) {
            actionsByName.put(action.getName(), action);
            actionsByClass.put(type, action);
            return true;
        }
        return false;
    }

    public static boolean register(@NotNull AbstractBotAction<?> action) {
        return register(action, action.getClass());
    }

    public static boolean unregister(@NotNull String name) {
        AbstractBotAction<?> action = actionsByName.remove(name);
        if (action != null) {
            actionsByClass.remove(action.getClass());
            return true;
        }
        return false;
    }

    @NotNull
    @Contract(pure = true)
    public static Collection<AbstractBotAction<?>> getAll() {
        return actionsByName.values();
    }

    @NotNull
    public static Set<String> getNames() {
        return actionsByName.keySet();
    }

    @Nullable
    public static AbstractBotAction<?> getForName(String name) {
        return actionsByName.get(name);
    }

    @Nullable
    public static AbstractBotAction<?> getForClass(@NotNull Class<?> type) {
        return actionsByClass.get(type);
    }
}
