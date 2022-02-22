package com.deltaclient.client.v1_7.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import com.deltaclient.common.bridge.text.ITextBridge;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TextRenderer.class)
public abstract class TextRendererMixin implements ITextRendererBridge {
    @Shadow
    public int fontHeight;

    @Shadow
    public abstract int draw(String par1, int par2, int par3, int par4);

    @Shadow
    public abstract int getStringWidth(String par1);

    @Override
    public int bridge$draw(IMatrixStackBridge matrices, @NotNull String text, float x, float y, int color) {
        return draw(text, (int) x, (int) y, color);
    }

    @Override
    public int bridge$draw(@Nullable IMatrixStackBridge matrices, @NotNull ITextBridge text, float x, float y, int color) {
        if (text instanceof TranslatableText) {
            return draw(I18n.translate(((TranslatableText) text).getKey()), (int) x, (int) y, color);
        }

        return draw(((Text) text).asString(), (int) x, (int) y, color);
    }

    @Override
    public int bridge$getWidth(@NotNull String text) {
        return getStringWidth(text);
    }

    @Override
    public int bridge$getWidth(@NotNull ITextBridge text) {
        if (text instanceof TranslatableText) {
            return getStringWidth(I18n.translate(((TranslatableText) text).getKey()));
        }

        return getStringWidth(((Text) text).asString());
    }

    @Override
    public int impl$getHeight() {
        return fontHeight;
    }
}
