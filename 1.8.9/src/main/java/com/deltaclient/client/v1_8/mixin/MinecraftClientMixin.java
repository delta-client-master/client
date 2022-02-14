package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.client.v1_8.util.LWJGLDisplayImpl;
import com.deltaclient.common.Delta;
import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.bridge.lang.ILanguageManagerBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.LanguageManager;
import org.jetbrains.annotations.NotNull;
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
    private LanguageManager languageManager;

    @Inject(method = "initializeGame", at = @At("HEAD"))
    void initializeGameHead(CallbackInfo ci) {
        Delta.mc = this;
        Delta.lwjglDisplay = new LWJGLDisplayImpl();
    }

    @Inject(method = "initializeGame", at = @At("RETURN"))
    void initializeGame(CallbackInfo ci) {
        Delta.onGameStart("1.8.9");
    }

    @Override
    public @Nullable IClientPlayerEntityBridge getClientPlayer() {
        return (IClientPlayerEntityBridge) player;
    }

    @NotNull
    @Override
    public ILanguageManagerBridge getLanguageManager() {
        return (ILanguageManagerBridge) languageManager;
    }

    @Override
    public long getWindowHandle() {
        return -1;
    }
}
