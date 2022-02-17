package com.deltaclient.client.v1_7.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import net.minecraft.client.font.TextRenderer;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin implements ITextRendererBridge {
    @Shadow
    public abstract int draw(String par1, int par2, int par3, int par4);

    @Shadow
    public abstract int getStringWidth(String par1);

    @Override
    public int bridge$draw(IMatrixStackBridge matrices, @NotNull String text, float x, float y, int color) {
        return draw(text, (int) x, (int) y, color);
    }

    @Override
    public int bridge$getWidth(@NotNull String text) {
        return getStringWidth(text);
    }
}
