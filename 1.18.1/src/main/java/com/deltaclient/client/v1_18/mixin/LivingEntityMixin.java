package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import com.deltaclient.common.bridge.entity.ILivingEntityBridge;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ILivingEntityBridge {
    @Shadow
    @Final
    private Map<StatusEffect, StatusEffectInstance> activeStatusEffects;

    @NotNull
    @Override
    public Collection<IStatusEffectInstanceBridge> bridge$getStatusEffects() {
        // uh, we definitely shouldn't have to do this FIXME
        return activeStatusEffects.values().stream().map(IStatusEffectInstanceBridge.class::cast).collect(Collectors.toList());
    }
}
