package com.deltaclient.client.v1_18.mixin.effect;

import com.deltaclient.common.bridge.effect.IStatusEffectBridge;
import com.deltaclient.common.bridge.text.ITranslatableTextBridge;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.text.TranslatableText;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffect.class)
public class StatusEffectMixin implements IStatusEffectBridge {
    @Shadow
    @Nullable
    private String translationKey;

    @NotNull
    @Override
    public ITranslatableTextBridge bridge$getName() {
        return (ITranslatableTextBridge) new TranslatableText(translationKey);
    }
}
