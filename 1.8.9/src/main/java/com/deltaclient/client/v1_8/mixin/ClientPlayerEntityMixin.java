package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntityBridge {
    public ClientPlayerEntityMixin(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Override
    public @NotNull String bridge$getUsername() {
        return super.getNameClear();
    }

    @Override
    public @NotNull UUID bridge$getUUID() {
        return super.getUuid();
    }
}
