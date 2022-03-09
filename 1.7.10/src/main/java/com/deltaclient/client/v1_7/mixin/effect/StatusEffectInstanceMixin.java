package com.deltaclient.client.v1_7.mixin.effect;

import com.deltaclient.common.bridge.effect.IStatusEffectBridge;
import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffectInstance.class)
public abstract class StatusEffectInstanceMixin implements IStatusEffectInstanceBridge {
    @Shadow
    private int effectId;

    @NotNull
    @Override
    public IStatusEffectBridge bridge$getType() {
        return (IStatusEffectBridge) StatusEffect.STATUS_EFFECTS[effectId];
    }

    @Override
    public boolean bridge$showIcon() {
        return true;
    }

    @Override
    public void bridge$showIcon(boolean showIcon) {
    }
}
