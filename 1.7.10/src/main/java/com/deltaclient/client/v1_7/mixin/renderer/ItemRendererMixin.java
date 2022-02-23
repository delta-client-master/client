package com.deltaclient.client.v1_7.mixin.renderer;

import com.deltaclient.common.bridge.item.IItemStackBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import com.mojang.blaze3d.platform.GLX;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements IItemRendererBridge {
    @Shadow
    public float zOffset;

    @Shadow
    public abstract void method_6920(TextRenderer par1, TextureManager par2, ItemStack par3, int par4, int par5);

    @Shadow
    public abstract void method_5178(TextRenderer par1, TextureManager par2, ItemStack par3, int par4, int par5, String par6);

    @Override
    public void bridge$renderInGui(@NotNull IItemStackBridge stack, int x, int y) {
        GuiLighting.enable();
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(32826);
        GL11.glEnable(2929);
        GLX.gl13MultiTexCoord2f(GLX.lightmapTextureUnit, (float) 240, (float) 240);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        zOffset = 200;
        method_6920(MinecraftClient.getInstance().textRenderer, MinecraftClient.getInstance().getTextureManager(), (ItemStack) stack, x, y);
        method_5178(MinecraftClient.getInstance().textRenderer, MinecraftClient.getInstance().getTextureManager(), (ItemStack) stack, x, y, "");
        zOffset = 0;

        GL11.glPopMatrix();
    }
}
