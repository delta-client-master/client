package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.state.GenericStateEvent;
import com.deltaclient.common.event.impl.state.StateEventType;
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
    private boolean tickMovement(KeyBinding instance) {
        return EventBus.INSTANCE.post(new GenericStateEvent(instance.isPressed(), StateEventType.SPRINT)).getState();
    }
}
