package com.deltaclient.client.v1_18.mixin.gui;

import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.feature.impl.statuseffect.StatusEffectHUDFeature;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractInventoryScreen.class)
public abstract class AbstractInventoryScreenMixin {
    private final FeatureService featureService = FeatureService.INSTANCE;
    private final StatusEffectHUDFeature statusEffectFeature = featureService.get(StatusEffectHUDFeature.class);

    @Inject(method = "drawStatusEffects", at = @At("HEAD"), cancellable = true)
    public void drawStatusEffects(CallbackInfo ci) {
        if (!statusEffectFeature.getEnabled()) {
            return;
        }

        if (statusEffectFeature.getShowEffectsInInventory()) {
            return;
        }

        if (!MinecraftClient.getInstance().player.getStatusEffects().isEmpty()) {
            ci.cancel();
        }
    }
}
