package com.deltaclient.client.v1_7.mixin;

import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.RenderOverlayEvent;
import com.mojang.blaze3d.platform.GLX;
import net.minecraft.client.gui.hud.InGameHud;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "method_979", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V", ordinal = 1, shift = At.Shift.AFTER))
    void render(float bl, boolean i, int j, int par4, CallbackInfo ci) {
        // GL code is for text rendering!

        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GLX.glBlendFuncSeparate(770, 771, 1, 0);
        EventBus.INSTANCE.post(new RenderOverlayEvent(null, bl));
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
