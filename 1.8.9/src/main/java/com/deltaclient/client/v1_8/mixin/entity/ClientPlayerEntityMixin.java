package com.deltaclient.client.v1_8.mixin.entity;

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.entity.player.IPlayerInventoryBridge;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.state.GenericStateEvent;
import com.deltaclient.common.event.impl.state.StateEventType;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements IClientPlayerEntityBridge {
    public ClientPlayerEntityMixin(World worldIn, GameProfile playerProfile) {
        super(worldIn, playerProfile);
    }

    @Shadow
    public abstract void sendMessage(Text par1);

    @Override
    public @NotNull String bridge$getUsername() {
        return super.getNameClear();
    }

    @Override
    public @NotNull UUID bridge$getUUID() {
        return super.getUuid();
    }

    @Override
    public void impl$sendMessage(@NotNull String message) {
        sendMessage(new LiteralText(message));
    }

    @NotNull
    @Override
    public IPlayerInventoryBridge bridge$getInventory() {
        return (IPlayerInventoryBridge) inventory;
    }

    @Override
    public float bridge$getSaturation() {
        return hungerManager.getSaturationLevel();
    }

    @Redirect(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/options/KeyBinding;isPressed()Z"))
    private boolean isPressedSprintOverride(KeyBinding instance) {
        return EventBus.INSTANCE.post(new GenericStateEvent(instance.isPressed(), StateEventType.SPRINT)).getState();
    }
}
