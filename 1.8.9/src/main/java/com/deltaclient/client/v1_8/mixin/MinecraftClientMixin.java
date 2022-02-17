package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.client.v1_8.session.SessionFactory;
import com.deltaclient.client.v1_8.util.LWJGLDisplayImpl;
import com.deltaclient.common.Delta;
import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.bridge.lang.ILanguageManagerBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.util.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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
    private LanguageManager languageManager;

    @Shadow
    public Session session;

    @Shadow
    private static int currentFps;

    @Shadow
    public TextRenderer textRenderer;

    @Inject(method = "initializeGame", at = @At("HEAD"))
    void initializeGameHead(CallbackInfo ci) {
        Delta.mc = this;
        Delta.sessionFactory = SessionFactory.INSTANCE;
        Delta.lwjglDisplay = new LWJGLDisplayImpl();
    }

    @Inject(method = "initializeGame", at = @At("RETURN"))
    void initializeGame(CallbackInfo ci) {
        Delta.onGameStart("1.8.9");
    }

    @Override
    public @Nullable IClientPlayerEntityBridge bridge$getClientPlayer() {
        return (IClientPlayerEntityBridge) player;
    }

    @NotNull
    @Override
    public ILanguageManagerBridge bridge$getLanguageManager() {
        return (ILanguageManagerBridge) languageManager;
    }

    @Nullable
    @Override
    public ISessionBridge bridge$getSession() {
        return (ISessionBridge) session;
    }

    @Override
    public void bridge$setSession(@Nullable ISessionBridge sessionBridge) {
        session = (Session) sessionBridge;
    }

    @Override
    public long bridge$getWindowHandle() {
        return -1;
    }

    @Override
    public int bridge$getCurrentFps() {
        return currentFps;
    }

    @NotNull
    @Override
    public ITextRendererBridge bridge$getTextRenderer() {
        return (ITextRendererBridge) textRenderer;
    }
}
