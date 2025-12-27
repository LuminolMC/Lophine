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

package org.leavesmc.leaves.protocol.core.invoker;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.leavesmc.leaves.protocol.core.LeavesProtocol;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractInvokerHolder<T> {

    protected final LeavesProtocol owner;
    protected final Method invoker;
    protected final T handler;
    protected final Class<?> returnType;
    protected final Class<?>[] parameterTypes;

    protected AbstractInvokerHolder(LeavesProtocol owner, Method invoker, T handler, @Nullable Class<?> returnType, @NotNull Class<?>... parameterTypes) {
        this.owner = owner;
        this.invoker = invoker;
        this.handler = handler;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;

        validateMethodSignature();
    }

    protected void validateMethodSignature() {
        if (returnType != null && !returnType.isAssignableFrom(invoker.getReturnType())) {
            throw new IllegalArgumentException("Return type mismatch in " + owner.getClass().getName() + "#" + invoker.getName() +
                    ": expected " + returnType.getName() + " but found " + invoker.getReturnType().getName());
        }

        Class<?>[] methodParamTypes = invoker.getParameterTypes();
        if (methodParamTypes.length != parameterTypes.length) {
            throw new IllegalArgumentException("Parameter count mismatch in " + owner.getClass().getName() + "#" + invoker.getName() +
                    ": expected " + parameterTypes.length + " but found " + methodParamTypes.length);
        }

        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].isAssignableFrom(methodParamTypes[i])) {
                throw new IllegalArgumentException("Parameter type mismatch in " + owner.getClass().getName() + "#" + invoker.getName() +
                        " at index " + i + ": expected " + parameterTypes[i].getName() + " but found " + methodParamTypes[i].getName());
            }
        }
    }

    public LeavesProtocol owner() {
        return owner;
    }

    public T handler() {
        return handler;
    }

    protected Object invoke0(boolean force, Object... args) {
        if (!force && !owner.isActive()) {
            return null;
        }
        try {
            return invoker.invoke(owner, args);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
