package com.deltaclient.client.v1_7.mixin.option;

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

    @Shadow
    public KeyBinding keyForward;

    @Shadow
    public KeyBinding keyBack;

    @Shadow
    public KeyBinding keyLeft;

    @Shadow
    public KeyBinding keyRight;

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

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyForward() {
        return (IKeyBindingBridge) keyForward;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyBack() {
        return (IKeyBindingBridge) keyBack;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyLeft() {
        return (IKeyBindingBridge) keyLeft;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyRight() {
        return (IKeyBindingBridge) keyRight;
    }
}