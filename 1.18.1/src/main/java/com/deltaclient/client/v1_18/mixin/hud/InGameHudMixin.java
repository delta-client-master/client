package com.deltaclient.client.v1_18.mixin.hud;

import com.deltaclient.common.bridge.math.IMatrixStackBridge;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.RenderOverlayEvent;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;getLastFrameDuration()F", shift = At.Shift.BEFORE))
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        EventBus.INSTANCE.post(new RenderOverlayEvent((IMatrixStackBridge) matrices, tickDelta));
    }
}
