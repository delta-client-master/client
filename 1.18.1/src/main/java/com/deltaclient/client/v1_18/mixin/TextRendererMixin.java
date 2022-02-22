package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import com.deltaclient.common.bridge.text.ITextBridge;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin implements ITextRendererBridge {
    @Final
    @Shadow
    public int fontHeight;

    @Shadow
    public abstract int draw(MatrixStack matrices, String text, float x, float y, int color);

    @Shadow
    public abstract int draw(MatrixStack matrices, Text text, float x, float y, int color);

    @Shadow
    public abstract int getWidth(String par1);

    @Shadow
    public abstract int getWidth(StringVisitable text);

    @Override
    public int bridge$draw(IMatrixStackBridge matrices, @NotNull String text, float x, float y, int color) {
        return draw((MatrixStack) matrices, text, x, y, color);
    }

    @Override
    public int bridge$draw(@Nullable IMatrixStackBridge matrices, @NotNull ITextBridge text, float x, float y, int color) {
        return draw((MatrixStack) matrices, (Text) text, x, y, color);
    }

    @Override
    public int bridge$getWidth(@NotNull String text) {
        return getWidth(text);
    }

    @Override
    public int bridge$getWidth(@NotNull ITextBridge text) {
        return getWidth((Text) text);
    }

    @Override
    public int impl$getHeight() {
        return fontHeight;
    }
}
