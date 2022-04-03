package com.deltaclient.client.v1_18.mixin.entity;

import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import com.deltaclient.common.bridge.entity.ILivingEntityBridge;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Map;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements ILivingEntityBridge {
    @Shadow
    @Final
    private Map activeStatusEffects;

    @NotNull
    @Override
    public Collection<IStatusEffectInstanceBridge> bridge$getStatusEffects() {
        return (Collection<IStatusEffectInstanceBridge>) activeStatusEffects.values();
    }
}
