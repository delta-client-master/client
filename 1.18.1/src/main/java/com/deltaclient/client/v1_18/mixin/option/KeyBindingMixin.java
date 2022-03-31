package com.deltaclient.client.v1_18.mixin.option;

import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public class KeyBindingMixin implements IKeyBindingBridge {
    @Shadow
    private InputUtil.Key boundKey;

    @Override
    public int bridge$getKeyCode() {
        return boundKey.getCode();
    }
}
