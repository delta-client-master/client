package com.deltaclient.client.v1_18.mixin.client;

import com.deltaclient.common.feature.impl.text.cps.CPSTracker;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {
    @Inject(method = "onMouseButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;isSpectator()Z", shift = At.Shift.BEFORE))
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (button == 0) {
            CPSTracker.INSTANCE.leftClick();
        } else if (button == 1) {
            CPSTracker.INSTANCE.rightClick();
        }
    }
}
