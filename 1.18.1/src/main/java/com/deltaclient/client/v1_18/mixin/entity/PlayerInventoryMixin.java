package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.entity.player.IPlayerInventoryBridge;
import com.deltaclient.common.bridge.item.IItemStackBridge;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements IPlayerInventoryBridge {
    @Shadow
    @Final
    public DefaultedList<ItemStack> armor;

    @NotNull
    @Override
    public List<IItemStackBridge> bridge$getArmor() {
        return armor.stream().map(IItemStackBridge.class::cast).collect(Collectors.toList());
    }
}
