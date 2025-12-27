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

package org.leavesmc.leaves.command.arguments;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import io.papermc.paper.command.brigadier.argument.CustomArgumentType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("ClassCanBeRecord")
public final class EnumArgumentType<T extends Enum<T>> implements CustomArgumentType.Converted<@NotNull T, @NotNull String> {
    private final Class<T> enumClass;

    @Contract(value = "_ -> new", pure = true)
    public static <T extends Enum<T>> @NotNull EnumArgumentType<T> fromEnum(Class<T> enumClass) {
        return new EnumArgumentType<>(enumClass);
    }

    private EnumArgumentType(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    public @NotNull T convert(@NotNull String nativeType) throws CommandSyntaxException {
        try {
            return Enum.valueOf(enumClass, nativeType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().create();
        }
    }

    @Override
    public <S> @NotNull CompletableFuture<Suggestions> listSuggestions(@NotNull CommandContext<S> context, @NotNull SuggestionsBuilder builder) {
        for (Enum<?> value : enumClass.getEnumConstants()) {
            String name = value.name().toLowerCase();
            if (name.startsWith(builder.getRemainingLowerCase())) {
                builder.suggest(name);
            }
        }
        return builder.buildFuture();
    }

    @Override
    public @NotNull ArgumentType<@NotNull String> getNativeType() {
        return StringArgumentType.word();
    }

    public Class<T> enumClass() {
        return enumClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        EnumArgumentType<?> that = (EnumArgumentType<?>) obj;
        return Objects.equals(this.enumClass, that.enumClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enumClass);
    }

    @Override
    public String toString() {
        return "EnumArgumentType[" +
                "enumClass=" + enumClass + ']';
    }

}
