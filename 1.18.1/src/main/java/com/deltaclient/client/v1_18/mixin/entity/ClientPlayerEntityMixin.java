package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.feature.impl.actiontoggle.ActionToggleFeature;
import com.deltaclient.common.util.BasicLazy;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntityBridge {
    private final BasicLazy<ActionToggleFeature> actionToggleFeature = new BasicLazy<>(() -> FeatureService.INSTANCE.get(ActionToggleFeature.class));

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    @Override
    public @NotNull String bridge$getUsername() {
        return super.getGameProfile().getName();
    }

    @Override
    public @NotNull UUID bridge$getUUID() {
        return super.getUuid();
    }

    @Override
    public void impl$sendMessage(@NotNull String message) {
        sendMessage(Text.of(message), false);
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"))
    private boolean isPressedSprintOverride(KeyBinding instance) {
        return (actionToggleFeature.get().getEnabled() && actionToggleFeature.get().getShouldOverrideSprint()) || instance.isPressed();
    }
}
