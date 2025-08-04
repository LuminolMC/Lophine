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

package org.leavesmc.leaves.util;

import fun.bm.lophine.config.modules.misc.ContainerExpansionConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ItemOverstackUtils {

    private static final List<ItemUtil> overstackUtils = List.of(
            new ShulkerBox()
            // new CurseEnchantedBook()
    );

    public static int getItemStackMaxCount(ItemStack stack) {
        int size;
        for (ItemUtil util : overstackUtils) {
            if ((size = util.getMaxServerStackCount(stack)) != -1) {
                return size;
            }
        }
        return stack.getMaxStackSize();
    }

    public static int getNetworkMaxCount(ItemStack stack) {
        int size;
        for (ItemUtil util : overstackUtils) {
            if ((size = util.getMaxClientStackCount(stack)) != -1) {
                return size;
            }
        }
        return stack.getMaxStackSize();
    }

    public static boolean tryStackItems(ItemEntity self, ItemEntity other) {
        for (ItemUtil util : overstackUtils) {
            if (util.tryStackItems(self, other)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasOverstackingItem() {
        return overstackUtils.stream().anyMatch(ItemUtil::isEnabled);
    }

    public static int getItemStackMaxCountReal(ItemStack stack) {
        CompoundTag nbt = Optional.ofNullable(stack.get(DataComponents.CUSTOM_DATA)).orElse(CustomData.EMPTY).copyTag();
        return nbt.getInt("Leaves.RealStackSize").orElse(stack.getMaxStackSize());
    }

    public static ItemStack encodeMaxStackSize(ItemStack itemStack) {
        int realMaxStackSize = getItemStackMaxCountReal(itemStack);
        int modifiedMaxStackSize = getNetworkMaxCount(itemStack);
        if (itemStack.getMaxStackSize() != modifiedMaxStackSize) {
            itemStack.set(DataComponents.MAX_STACK_SIZE, modifiedMaxStackSize);
            CompoundTag nbt = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            nbt.putInt("Leaves.RealStackSize", realMaxStackSize);
            itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }
        return itemStack;
    }

    public static ItemStack decodeMaxStackSize(ItemStack itemStack) {
        int realMaxStackSize = getItemStackMaxCountReal(itemStack);
        if (itemStack.getMaxStackSize() != realMaxStackSize) {
            itemStack.set(DataComponents.MAX_STACK_SIZE, realMaxStackSize);
            CompoundTag nbt = itemStack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
            nbt.remove("Leaves.RealStackSize");
            if (nbt.isEmpty()) {
                itemStack.remove(DataComponents.CUSTOM_DATA);
            } else {
                itemStack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
            }
        }
        return itemStack;
    }

    public static boolean isStackable(ItemStack itemStack) {
        return getItemStackMaxCount(itemStack) > 1 && (!itemStack.isDamageableItem() || !itemStack.isDamaged());
    }


    private interface ItemUtil {
        boolean isEnabled();

        boolean tryStackItems(ItemEntity self, ItemEntity other);

        // number -> modified count, -1 -> I don't care
        int getMaxServerStackCount(ItemStack stack);

        // number -> modified count, -1 -> I don't care
        default int getMaxClientStackCount(ItemStack stack) {
            return getMaxServerStackCount(stack);
        }
    }

    private static class ShulkerBox implements ItemUtil {
        public static boolean shulkerBoxCheck(@NotNull ItemStack stack1, @NotNull ItemStack stack2) {
            if (ContainerExpansionConfig.nbtShulkerStackable) {
                return Objects.equals(stack1.getComponents(), stack2.getComponents());
            }
            return shulkerBoxNoItem(stack1) && shulkerBoxNoItem(stack2) && Objects.equals(stack1.getComponents(), stack2.getComponents());
        }

        public static boolean shulkerBoxNoItem(@NotNull ItemStack stack) {
            return stack.getComponents().getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).stream().findAny().isEmpty();
        }

        @Override
        public boolean isEnabled() {
            return ContainerExpansionConfig.shulkerCount > 1;
        }

        @Override
        public boolean tryStackItems(ItemEntity self, ItemEntity other) {
            ItemStack selfStack = self.getItem();
            if (!isEnabled() ||
                    !(selfStack.getItem() instanceof BlockItem blockItem) ||
                    !(blockItem.getBlock() instanceof ShulkerBoxBlock)
            ) {
                return false;
            }

            ItemStack otherStack = other.getItem();
            if (selfStack.getItem() == otherStack.getItem()
                    && shulkerBoxCheck(selfStack, otherStack)
                    && selfStack.getCount() != ContainerExpansionConfig.shulkerCount) {
                int amount = Math.min(otherStack.getCount(), ContainerExpansionConfig.shulkerCount - selfStack.getCount());

                selfStack.grow(amount);
                self.setItem(selfStack);

                self.pickupDelay = Math.max(other.pickupDelay, self.pickupDelay);
                self.age = Math.min(other.getAge(), self.age);

                otherStack.shrink(amount);
                if (otherStack.isEmpty()) {
                    other.discard();
                } else {
                    other.setItem(otherStack);
                }
                return true;
            }
            return false;
        }

        @Override
        public int getMaxServerStackCount(ItemStack stack) {
            if (isEnabled() && stack.getItem() instanceof BlockItem bi &&
                    bi.getBlock() instanceof ShulkerBoxBlock && (ContainerExpansionConfig.nbtShulkerStackable || shulkerBoxNoItem(stack))) {
                return ContainerExpansionConfig.shulkerCount;
            }
            return -1;
        }
    }

// only use shulker box part
/*    public static class CurseEnchantedBook implements ItemUtil {
        public static boolean isCursedEnchantedBook(ItemStack stack) {
            ItemEnchantments enchantments = stack.getOrDefault(DataComponents.STORED_ENCHANTMENTS, ItemEnchantments.EMPTY);
            if (enchantments.size() != 1) {
                return false;
            }
            return stack.getBukkitStack().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.BINDING_CURSE) == 1 ||
                stack.getBukkitStack().getEnchantmentLevel(org.bukkit.enchantments.Enchantment.BINDING_CURSE) == 1;
        }

        @Override
        public boolean isEnabled() {
            return LeavesConfig.modify.oldMC.allowGrindstoneOverstacking;
        }

        @Override
        public boolean tryStackItems(ItemEntity self, ItemEntity other) {
            return false;
        }

        @Override
        public int getMaxServerStackCount(ItemStack stack) {
            return -1;
        }

        @Override
        public int getMaxClientStackCount(ItemStack stack) {
            if (isEnabled() && isCursedEnchantedBook(stack)) {
                return 2;
            }
            return -1;
        }
    }*/
}