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

    @Shadow
    @Final
    public KeyBinding keySneak;

    @Shadow
    @Final
    public KeyBinding keyForward;

    @Shadow
    @Final
    public KeyBinding keyBack;

    @Shadow
    @Final
    public KeyBinding keyLeft;

    @Shadow
    @Final
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
