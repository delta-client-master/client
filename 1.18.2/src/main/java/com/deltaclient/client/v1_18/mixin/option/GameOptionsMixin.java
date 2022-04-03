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
    @Shadow @Final public KeyBinding sprintKey;

    @Shadow @Final public KeyBinding sneakKey;

    @Shadow @Final public KeyBinding forwardKey;

    @Shadow @Final public KeyBinding backKey;

    @Shadow @Final public KeyBinding leftKey;

    @Shadow @Final public KeyBinding rightKey;

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeySprint() {
        return (IKeyBindingBridge) sprintKey;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeySneak() {
        return (IKeyBindingBridge) sneakKey;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyForward() {
        return (IKeyBindingBridge) forwardKey;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyBack() {
        return (IKeyBindingBridge) backKey;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyLeft() {
        return (IKeyBindingBridge) leftKey;
    }

    @NotNull
    @Override
    public IKeyBindingBridge bridge$getKeyRight() {
        return (IKeyBindingBridge) rightKey;
    }
}
