package com.deltaclient.client.v1_18.mixin.client;

import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.input.InputType;
import com.deltaclient.common.event.impl.input.KeyboardInputEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/InputUtil;fromKeyCode(II)Lnet/minecraft/client/util/InputUtil$Key;", shift = At.Shift.AFTER))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        EventBus.INSTANCE.post(new KeyboardInputEvent(key, action == 0 ? InputType.RELEASE : InputType.PRESS));
    }
}
