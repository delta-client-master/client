package com.deltaclient.client.v1_18.mixin.client;

import com.deltaclient.client.v1_18.gui.DrawableHelperBridgeImpl;
import com.deltaclient.client.v1_18.language.I18nBridgeImpl;
import com.deltaclient.client.v1_18.session.SessionFactory;
import com.deltaclient.client.v1_18.util.LWJGLDisplayImpl;
import com.deltaclient.common.DeltaClient;
import com.deltaclient.common.bridge.client.IMinecraftClientBridge;
import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.font.ITextRendererBridge;
import com.deltaclient.common.bridge.language.ILanguageManagerBridge;
import com.deltaclient.common.bridge.option.IGameOptionsBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge;
import com.deltaclient.common.model.GameVersion;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.client.util.Session;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin implements IMinecraftClientBridge {
    @Shadow
    private static int currentFps;
    @Shadow
    public ClientPlayerEntity player;
    @Shadow
    public Session session;
    @Shadow
    @Final
    public TextRenderer textRenderer;
    @Shadow
    @Final
    private Window window;
    @Shadow
    @Final
    private LanguageManager languageManager;
    @Shadow
    @Final
    private StatusEffectSpriteManager statusEffectSpriteManager;

    @Shadow
    @Final
    private ItemRenderer itemRenderer;

    @Shadow
    @Final
    private MinecraftSessionService sessionService;

    @Shadow
    public abstract void scheduleStop();

    @Shadow
    @Final
    public GameOptions options;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(RunArgs args, CallbackInfo ci) {
        DeltaClient.INSTANCE.onGameStart(
                GameVersion.V1_18_1,
                this,
                SessionFactory.INSTANCE,
                new LWJGLDisplayImpl(),
                DrawableHelperBridgeImpl.INSTANCE,
                I18nBridgeImpl.INSTANCE
        );
    }

    @Inject(method = "stop", at = @At("HEAD"))
    private void stop(CallbackInfo ci) {
        DeltaClient.INSTANCE.onGameQuit();
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

    @NotNull
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
        return window.getHandle();
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
        return (IStatusEffectSpriteManagerBridge) statusEffectSpriteManager;
    }

    @NotNull
    @Override
    public IItemRendererBridge bridge$getItemRenderer() {
        return (IItemRendererBridge) itemRenderer;
    }

    @NotNull
    @Override
    public MinecraftSessionService bridge$getSessionService() {
        return sessionService;
    }

    @Override
    public void bridge$close() {
        scheduleStop();
    }

    @NotNull
    @Override
    public IGameOptionsBridge bridge$getOptions() {
        return (IGameOptionsBridge) options;
    }
}
