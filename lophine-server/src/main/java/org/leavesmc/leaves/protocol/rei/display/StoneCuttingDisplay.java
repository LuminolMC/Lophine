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

package org.leavesmc.leaves.protocol.rei.display;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import org.bukkit.craftbukkit.CraftRegistry;
import org.jetbrains.annotations.NotNull;
import org.leavesmc.leaves.protocol.rei.ingredient.EntryIngredient;

import java.util.List;
import java.util.Optional;

/**
 * see me.shedaniel.rei.plugin.common.displays.DefaultStoneCuttingDisplay
 */
public class StoneCuttingDisplay extends Display {
    private static final StreamCodec<RegistryFriendlyByteBuf, StoneCuttingDisplay> CODEC = StreamCodec.composite(
            EntryIngredient.CODEC.apply(ByteBufCodecs.list()),
            StoneCuttingDisplay::getInputEntries,
            EntryIngredient.CODEC.apply(ByteBufCodecs.list()),
            StoneCuttingDisplay::getOutputEntries,
            ByteBufCodecs.optional(ResourceLocation.STREAM_CODEC),
            StoneCuttingDisplay::getOptionalLocation,
            StoneCuttingDisplay::of
    );

    private static final ResourceLocation SERIALIZER_ID = ResourceLocation.tryBuild("minecraft", "default/stone_cutting");

    public StoneCuttingDisplay(@NotNull List<EntryIngredient> inputs, @NotNull List<EntryIngredient> outputs, @NotNull ResourceLocation id) {
        super(inputs, outputs, id);
    }

    public StoneCuttingDisplay(RecipeHolder<StonecutterRecipe> recipeHolder) {
        this(
                List.of(EntryIngredient.ofIngredient(recipeHolder.value().input())),
                List.of(EntryIngredient.of(recipeHolder.value().assemble(new SingleRecipeInput(ItemStack.EMPTY), CraftRegistry.getMinecraftRegistry()))),
                recipeHolder.id().location()
        );
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private static StoneCuttingDisplay of(@NotNull List<EntryIngredient> inputs, @NotNull List<EntryIngredient> outputs, @NotNull Optional<ResourceLocation> id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ResourceLocation getSerializerId() {
        return SERIALIZER_ID;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, StoneCuttingDisplay> streamCodec() {
        return CODEC;
    }
}
