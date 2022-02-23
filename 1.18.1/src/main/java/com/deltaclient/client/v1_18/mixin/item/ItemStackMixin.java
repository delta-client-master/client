package com.deltaclient.client.v1_18.mixin.item;

import com.deltaclient.common.bridge.item.IItemBridge;
import com.deltaclient.common.bridge.item.IItemStackBridge;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IItemStackBridge {
    @Final
    @Shadow
    private Item item;

    @Shadow
    public abstract int getDamage();

    @Nullable
    @Override
    public IItemBridge bridge$getItem() {
        return (IItemBridge) item;
    }

    @Override
    public int bridge$getDamage() {
        return getDamage();
    }

    @Override
    public int bridge$getMaxDamage() {
        return item.getMaxDamage();
    }
}
