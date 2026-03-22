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

package org.leavesmc.leaves.bot;

import com.google.common.base.Charsets;
import fun.bm.lophine.config.carpet.modules.FakePlayerCompatConfig;
import fun.bm.lophine.config.modules.function.FakeplayerConfig;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class BotUtil {

    public static void replenishment(@NotNull ItemStack itemStack, NonNullList<ItemStack> itemStackList) {
        int count = itemStack.getMaxStackSize() / 2;
        if (itemStack.getCount() <= 8 && count > 8) {
            if (pullMatchingStack(itemStack, itemStackList, count)) {
                return;
            }
            if (FakePlayerCompatConfig.fakePlayerAutoReplenishmentFormShulkerBox) {
                pullMatchingStackFromShulkerBox(itemStack, itemStackList, count);
            }
        }
    }

    private static boolean pullMatchingStack(@NotNull ItemStack targetStack, NonNullList<ItemStack> itemStackList, int transferLimit) {
        for (ItemStack inventoryStack : itemStackList) {
            if (inventoryStack == ItemStack.EMPTY || inventoryStack == targetStack) {
                continue;
            }

            if (ItemStack.isSameItemSameComponents(inventoryStack, targetStack)) {
                if (inventoryStack.getCount() > transferLimit) {
                    targetStack.setCount(targetStack.getCount() + transferLimit);
                    inventoryStack.setCount(inventoryStack.getCount() - transferLimit);
                } else {
                    targetStack.setCount(targetStack.getCount() + inventoryStack.getCount());
                    inventoryStack.setCount(0);
                }
                return true;
            }
        }

        return false;
    }

    private static boolean pullMatchingStackFromShulkerBox(@NotNull ItemStack targetStack, NonNullList<ItemStack> itemStackList, int transferLimit) {
        for (ItemStack containerStack : itemStackList) {
            if (containerStack == ItemStack.EMPTY || !containerStack.is(ItemTags.SHULKER_BOXES)) {
                continue;
            }

            ItemContainerContents contents = containerStack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
            NonNullList<ItemStack> shulkerItems = NonNullList.withSize(contents.items.size(), ItemStack.EMPTY);
            contents.copyInto(shulkerItems);

            for (int slot = 0; slot < shulkerItems.size(); slot++) {
                ItemStack shulkerStack = shulkerItems.get(slot);
                if (shulkerStack.isEmpty() || !ItemStack.isSameItemSameComponents(shulkerStack, targetStack)) {
                    continue;
                }

                int moved = Math.min(Math.min(transferLimit, shulkerStack.getCount()), targetStack.getMaxStackSize() - targetStack.getCount());
                if (moved <= 0) {
                    return false;
                }

                targetStack.grow(moved);
                shulkerStack.shrink(moved);
                shulkerItems.set(slot, shulkerStack.isEmpty() ? ItemStack.EMPTY : shulkerStack);
                containerStack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(shulkerItems));
                return true;
            }
        }

        return false;
    }

    public static void replaceTool(@NotNull EquipmentSlot slot, @NotNull ServerBot bot) {
        ItemStack itemStack = bot.getItemBySlot(slot);
        for (int i = 0; i < 36; i++) {
            ItemStack itemStack1 = bot.getInventory().getItem(i);
            if (itemStack1 == ItemStack.EMPTY || itemStack1 == itemStack) {
                continue;
            }

            if (itemStack1.getItem().getClass() == itemStack.getItem().getClass() && !isDamage(itemStack1, 10)) {
                ItemStack itemStack2 = itemStack1.copy();
                bot.getInventory().setItem(i, itemStack);
                bot.setItemSlot(slot, itemStack2);
                return;
            }
        }

        for (int i = 0; i < 36; i++) {
            ItemStack itemStack1 = bot.getInventory().getItem(i);
            if (itemStack1 == ItemStack.EMPTY && itemStack1 != itemStack) {
                bot.getInventory().setItem(i, itemStack);
                bot.setItemSlot(slot, ItemStack.EMPTY);
                return;
            }
        }
    }

    public static boolean isDamage(@NotNull ItemStack item, int minDamage) {
        return item.isDamageableItem() && (item.getMaxDamage() - item.getDamageValue()) <= minDamage;
    }

    @NotNull
    public static UUID getBotUUID(@NotNull BotCreateState state) {
        return getBotUUID(state.fullName());
    }

    public static UUID getBotUUID(@NotNull String fullName) {
        return UUID.nameUUIDFromBytes(("Fakeplayer:" + fullName).getBytes(Charsets.UTF_8));
    }

    public static UUID getBotLevel(@NotNull String fullName, BotDataStorage botDataStorage) {
        UUID uuid = getBotUUID(fullName);
        Optional<CompoundTag> tagOptional = botDataStorage.read(uuid.toString());
        if (tagOptional.isEmpty()) {
            return null;
        }
        CompoundTag tag = tagOptional.get();
        Optional<Long> worldUUIDMost = tag.getLong("WorldUUIDMost");
        Optional<Long> worldUUIDLeast = tag.getLong("WorldUUIDLeast");
        if (worldUUIDMost.isEmpty() || worldUUIDLeast.isEmpty()) {
            return null;
        }
        return new UUID(worldUUIDMost.get(), worldUUIDLeast.get());
    }

    public static String getFullName(String inputName) {
        return FakeplayerConfig.prefix + inputName + FakeplayerConfig.suffix;
    }

    public static boolean isCreateLegal(@NotNull String name) {
        if (!name.matches("^[a-zA-Z0-9_]{4,16}$")) {
            return false;
        }

        if (Bukkit.getPlayerExact(name) != null || BotList.INSTANCE.getBotByName(name) != null) {
            return false;
        }

        if (FakeplayerConfig.unableNames.contains(name)) {
            return false;
        }

        return BotList.INSTANCE.bots.size() < FakeplayerConfig.limit;
    }
}
