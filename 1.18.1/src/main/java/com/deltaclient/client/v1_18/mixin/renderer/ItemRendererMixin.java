package com.deltaclient.client.v1_18.mixin.renderer;

import com.deltaclient.common.bridge.item.IItemStackBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements IItemRendererBridge {
    @Shadow
    public abstract void renderInGui(ItemStack stack, int x, int y);

    @Override
    public void bridge$renderInGui(@NotNull IItemStackBridge stack, int x, int y) {
        RenderSystem.clearColor(1f, 1f, 1f, 1f);
        renderInGui((ItemStack) stack, x, y);
    }
}
