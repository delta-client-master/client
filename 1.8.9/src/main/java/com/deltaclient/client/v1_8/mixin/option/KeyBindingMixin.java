package com.deltaclient.client.v1_8.mixin.option;

import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import com.deltaclient.common.feature.impl.text.cps.CPSTracker;
import net.minecraft.client.options.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements IKeyBindingBridge {
    @Shadow
    private int code;

    @Shadow
    private boolean pressed;

    @Inject(method = "onKeyPressed", at = @At("HEAD"))
    private static void onKeyPressed(int keyCode, CallbackInfo ci) {
        if (keyCode < -1) {
            int button = keyCode + 100;
            if (button == 0) {
                CPSTracker.INSTANCE.leftClick();
            } else if (button == 1) {
                CPSTracker.INSTANCE.rightClick();
            }
        }
    }

    @Override
    public int bridge$getKeyCode() {
        return code;
    }

    @NotNull
    @Override
    public String bridge$getKeyName() {
        return Keyboard.getKeyName(code);
    }

    @Override
    public boolean bridge$isPressed() {
        return pressed;
    }
}
