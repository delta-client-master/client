package com.deltaclient.client.v1_18.mixin.input;

import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.state.GenericStateEvent;
import com.deltaclient.common.event.impl.state.StateEventType;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z", ordinal = 5))
    private boolean tick(KeyBinding instance) {
        return EventBus.INSTANCE.post(new GenericStateEvent(instance.isPressed(), StateEventType.SNEAK)).getState();
    }
}
