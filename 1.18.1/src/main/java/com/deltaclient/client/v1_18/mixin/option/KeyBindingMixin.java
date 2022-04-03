package com.deltaclient.client.v1_18.mixin.option;

import com.deltaclient.common.bridge.option.IKeyBindingBridge;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin implements IKeyBindingBridge {
    @Shadow
    private InputUtil.Key boundKey;
    @Shadow
    private boolean pressed;

    @Override
    public int bridge$getKeyCode() {
        return boundKey.getCode();
    }

    @NotNull
    @Override
    public String bridge$getKeyName() {
        return Objects.requireNonNull(GLFW.glfwGetKeyName(boundKey.getCode(), 0));
    }

    @Override
    public boolean bridge$isPressed() {
        return pressed;
    }
}
