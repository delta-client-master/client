package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.entity.player.IPlayerEntityBridge;
import com.deltaclient.common.bridge.entity.player.IPlayerInventoryBridge;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements IPlayerEntityBridge {
    @Shadow
    protected HungerManager hungerManager;
    @Shadow
    @Final
    private PlayerInventory inventory;

    @NotNull
    @Override
    public IPlayerInventoryBridge bridge$getInventory() {
        return (IPlayerInventoryBridge) inventory;
    }

    @Override
    public float bridge$getSaturation() {
        return hungerManager.getSaturationLevel();
    }
}
