package com.deltaclient.client.v1_8.mixin.client;

import com.deltaclient.client.v1_8.language.I18nBridgeImpl;
import com.deltaclient.client.v1_8.render.DrawableHelperBridgeImpl;
import com.deltaclient.client.v1_8.session.SessionFactory;
import com.deltaclient.client.v1_8.texture.SpriteEffectSpriteManagerImpl;
import com.deltaclient.client.v1_8.util.LWJGLDisplayImpl;
import com.deltaclient.common.DeltaClient;
import com.deltaclient.common.bridge.client.IMinecraftClientBridge;
import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.font.ITextRendererBridge;
import com.deltaclient.common.bridge.language.ILanguageManagerBridge;
import com.deltaclient.common.bridge.option.IGameOptionsBridge;
import com.deltaclient.common.bridge.render.IItemRendererBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.input.InputType;
import com.deltaclient.common.event.impl.input.KeyboardInputEvent;
import com.deltaclient.common.model.GameVersion;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.resource.language.LanguageManager;
import net.minecraft.client.util.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
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

    @Shadow
    @Final
    private MinecraftSessionService sessionService;

    @Shadow
    public abstract void scheduleStop();

    @Shadow
    public GameOptions options;

    @Inject(method = "initializeGame", at = @At("RETURN"))
    private void initializeGame(CallbackInfo ci) {
        DeltaClient.INSTANCE.onGameStart(
                GameVersion.V1_8_9,
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

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/KeyBinding;setKeyPressed(IZ)V", ordinal = 1))
    private void tick(CallbackInfo ci) {
        int k = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
        EventBus.INSTANCE.post(new KeyboardInputEvent(k, Keyboard.getEventKeyState() ? InputType.PRESS : InputType.RELEASE));
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
