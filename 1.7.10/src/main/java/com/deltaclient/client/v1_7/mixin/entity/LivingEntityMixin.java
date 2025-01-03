package com.deltaclient.client.v1_7.mixin.entity;

import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import com.deltaclient.common.bridge.entity.ILivingEntityBridge;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

@SuppressWarnings({"rawtypes", "unchecked"})
@Mixin(LivingEntity.class)
public class LivingEntityMixin implements ILivingEntityBridge {
    @Shadow
    @Final
    private HashMap field_6797;

    @NotNull
    @Override
    public Collection<IStatusEffectInstanceBridge> bridge$getStatusEffects() {
        return (Collection<IStatusEffectInstanceBridge>) field_6797.values();
    }
}
