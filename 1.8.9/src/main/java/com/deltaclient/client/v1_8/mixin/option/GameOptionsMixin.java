package com.deltaclient.client.v1_8.mixin.option;

import com.deltaclient.common.bridge.option.IGameOptionsBridge;
import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IGameOptionsBridge {
    @Shadow
    public KeyBinding keySprint;

    @Shadow
    public KeyBinding keySneak;

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeySprint() {
        return (IKeyBindingBridge) keySprint;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeySneak() {
        return (IKeyBindingBridge) keySneak;
    }
}
