package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntityBridge {
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
}
