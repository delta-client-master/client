package com.deltaclient.client.v1_8.mixin.client;

import com.deltaclient.client.v1_8.language.I18nBridgeImpl;
import com.deltaclient.client.v1_8.render.DrawableHelperBridgeImpl;
import com.deltaclient.client.v1_8.session.SessionFactory;
import com.deltaclient.client.v1_8.texture.SpriteEffectSpriteManagerImpl;
import com.deltaclient.client.v1_8.util.LWJGLDisplayImpl;
import com.deltaclient.common.Delta;
import com.deltaclient.common.bridge.client.IMinecraftClientBridge;
import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.font.ITextRendererBridge;
import com.deltaclient.common.bridge.language.ILanguageManagerBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.util.Session;
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
    private static int currentFps;
    @Shadow
    public Session session;
    @Shadow
    public TextRenderer textRenderer;
    @Shadow
    @Nullable
    public ClientPlayerEntity player;
    @Shadow
    private LanguageManager languageManager;

    @Shadow
    private ItemRenderer itemRenderer;

    @Inject(method = "initializeGame", at = @At("HEAD"))
    private void initializeGameHead(CallbackInfo ci) {
        Delta.mc = this;
        Delta.sessionFactory = SessionFactory.INSTANCE;
        Delta.lwjglDisplay = new LWJGLDisplayImpl();
        Delta.drawableHelper = DrawableHelperBridgeImpl.INSTANCE;
        Delta.i18nBridge = I18nBridgeImpl.INSTANCE;
    }

    @Inject(method = "initializeGame", at = @At("RETURN"))
    private void initializeGame(CallbackInfo ci) {
        Delta.onGameStart("1.8.9");
    }

    @Inject(method = "stop", at = @At("HEAD"))
    private void stop(CallbackInfo ci) {
        Delta.onGameQuit();
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

    @NotNull
    @Override
    public IStatusEffectSpriteManagerBridge bridge$getStatusEffectSpriteManager() {
        return SpriteEffectSpriteManagerImpl.INSTANCE;
    }

    @NotNull
    @Override
    public IItemRendererBridge bridge$getItemRenderer() {
        return (IItemRendererBridge) itemRenderer;
    }
}
