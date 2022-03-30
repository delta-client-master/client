package com.deltaclient.client.v1_8.mixin.hud;

import com.deltaclient.common.Delta;
import com.deltaclient.common.feature.impl.chat.ChatFeature;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;fill(IIIII)V"))
    private void fill(int x1, int y1, int x2, int y2, int color) {
        ChatFeature chat = Delta.featureService.get(ChatFeature.class);
        if (chat.getEnabled() && chat.getDrawBackground()) {
            DrawableHelper.fill(x1, y1, x2, y2, color);
        }
    }
}