package com.deltaclient.client.v1_7.mixin;

import com.deltaclient.client.v1_7.language.I18nBridgeImpl;
import com.deltaclient.client.v1_7.session.SessionFactory;
import com.deltaclient.client.v1_7.texture.SpriteEffectSpriteManagerImpl;
import com.deltaclient.client.v1_7.render.DrawableHelperBridgeImpl;
import com.deltaclient.client.v1_7.util.LWJGLDisplayImpl;
import com.deltaclient.common.Delta;
import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.bridge.lang.ILanguageManagerBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.render.ITextRendererBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge;
import net.minecraft.class_481;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
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
    private static int currentFps;
    @Shadow
    public class_481 field_3805;
    @Shadow
    public Session session;
    @Shadow
    public TextRenderer textRenderer;
    @Shadow
    private LanguageManager languageManager;

    @Inject(method = "initializeGame", at = @At("HEAD"))
    void initializeGameHead(CallbackInfo ci) {
        Delta.mc = this;
        Delta.sessionFactory = SessionFactory.INSTANCE;
        Delta.lwjglDisplay = new LWJGLDisplayImpl();
        Delta.drawableHelper = DrawableHelperBridgeImpl.INSTANCE;
        Delta.i18nBridge = I18nBridgeImpl.INSTANCE;
    }

    @Inject(method = "initializeGame", at = @At("RETURN"))
    void initializeGame(CallbackInfo ci) {
        Delta.onGameStart("1.7.10");
    }

    @Override
    public @Nullable IClientPlayerEntityBridge bridge$getClientPlayer() {
        return (IClientPlayerEntityBridge) field_3805;
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
}
