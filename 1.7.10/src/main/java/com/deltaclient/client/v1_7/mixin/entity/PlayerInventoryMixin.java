package com.deltaclient.client.v1_7.mixin.entity;

import com.deltaclient.common.bridge.entity.player.IPlayerInventoryBridge;
import com.deltaclient.common.bridge.item.IItemStackBridge;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements IPlayerInventoryBridge {
    @Shadow
    public ItemStack[] armor;

    @NotNull
    @Override
    public List<IItemStackBridge> bridge$getArmor() {
        return Arrays.stream(armor).map(i -> {
            if (i == null) {
                return new ItemStack((Item) null);
            }

            return i;
        }).map(IItemStackBridge.class::cast).collect(Collectors.toList());
    }
}
