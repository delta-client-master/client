package com.deltaclient.client.v1_18.mixin.item;

import com.deltaclient.common.bridge.item.IItemBridge;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public class ItemMixin implements IItemBridge {
}
