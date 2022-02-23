package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntityBridge {
    @Shadow
    public abstract void sendMessage(Text message, boolean actionBar);

    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

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
}
