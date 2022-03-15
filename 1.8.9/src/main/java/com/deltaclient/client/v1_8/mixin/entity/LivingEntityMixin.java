package com.deltaclient.client.v1_8.mixin.entity;

import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import com.deltaclient.common.bridge.entity.ILivingEntityBridge;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked", "rawtypes"})
@Mixin(LivingEntity.class)
public class LivingEntityMixin implements ILivingEntityBridge {
    @Shadow
    @Final
    private Map statusEffects;

    @NotNull
    @Override
    public Collection<IStatusEffectInstanceBridge> bridge$getStatusEffects() {
        return (Collection<IStatusEffectInstanceBridge>) statusEffects.values();
    }
}
