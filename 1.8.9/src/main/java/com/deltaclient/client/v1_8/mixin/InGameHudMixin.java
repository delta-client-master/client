package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.client.v1_8.render.MatrixStack;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.RenderOverlayEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlStateManager;color4f(FFFF)V", ordinal = 1, shift = At.Shift.AFTER))
    void render(float j1, CallbackInfo ci) {
        EventBus.INSTANCE.post(new RenderOverlayEvent(MatrixStack.INSTANCE, j1));
    }
}
