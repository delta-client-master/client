package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.client.v1_18.util.LWJGLDisplayImpl;
import com.deltaclient.common.Delta;
import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin implements IMinecraftClientBridge {
    @Shadow
    public ClientPlayerEntity player;

    @Shadow
    @Final
    private Window window;

    @Inject(method = "<init>", at = @At("RETURN"))
    void init(RunArgs args, CallbackInfo ci) {
        Delta.mc = this;
        Delta.lwjglDisplay = new LWJGLDisplayImpl();

        Delta.onGameStart("1.18.1");
    }

    @Override
    public @Nullable IClientPlayerEntityBridge getClientPlayer() {
        return (IClientPlayerEntityBridge) player;
    }

    @Override
    public long getWindowHandle() {
        return window.getHandle();
    }
}
