package com.deltaclient.client.v1_18.mixin.option;

import com.deltaclient.common.bridge.option.IGameOptionsBridge;
import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameOptions.class)
public class GameOptionsMixin implements IGameOptionsBridge {
    @Final
    @Shadow
    public KeyBinding keySprint;

    @Shadow @Final public KeyBinding keySneak;

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
