package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.effect.IStatusEffectBridge;
import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin implements IStatusEffectInstanceBridge {
    @Shadow
    @Final
    private StatusEffect type;

    @Shadow
    private boolean showIcon;

    @NotNull
    @Override
    public IStatusEffectBridge bridge$getType() {
        return (IStatusEffectBridge) type;
    }

    @Override
    public boolean bridge$showIcon() {
        return showIcon;
    }

    @Override
    public void setShowIcon(boolean showIcon) {
        this.showIcon = showIcon;
    }
}
