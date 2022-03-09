package com.deltaclient.client.v1_8.mixin.renderer;

import com.deltaclient.common.bridge.item.IItemStackBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements IItemRendererBridge {
    @Shadow
    public abstract void renderItem(ItemStack par1, int par2, int par3);

    @Override
    public void bridge$renderInGui(@NotNull IItemStackBridge stack, int x, int y) {
        renderItem((ItemStack) stack, x, y);
    }
}
