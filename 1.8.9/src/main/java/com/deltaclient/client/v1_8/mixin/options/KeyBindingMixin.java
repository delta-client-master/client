package com.deltaclient.client.v1_8.mixin.options;

import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements IKeyBindingBridge {
    @Shadow private int code;

    @Override
    public int bridge$getKeyCode() {
        return code;
    }
}
