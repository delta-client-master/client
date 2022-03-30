package com.deltaclient.client.v1_18.mixin.hud;

import com.deltaclient.common.Delta;
import com.deltaclient.common.feature.impl.chat.ChatFeature;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"))
    private void fill(MatrixStack matrixStack, int x1, int y1, int x2, int y2, int color) {
        ChatFeature chat = Delta.featureService.get(ChatFeature.class);
        if (chat.getEnabled() && chat.getDrawBackground()) {
            DrawableHelper.fill(matrixStack, x1, y1, x2, y2, color);
        }
    }
}