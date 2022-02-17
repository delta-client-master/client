package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin implements ITextRendererBridge {
    @Shadow
    public abstract int draw(MatrixStack matrices, String text, float x, float y, int color);

    @Override
    public int bridge$draw(IMatrixStackBridge matrices, @NotNull String text, float x, float y, int color) {
        return draw((MatrixStack) matrices, text, x, y, color);
    }
}
