package com.deltaclient.client.v1_8.mixin.gui;

import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.feature.impl.statuseffect.StatusEffectHUDFeature;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends HandledScreen {
    public InventoryScreenMixin(ScreenHandler screenHandler) {
        super(screenHandler);
    }

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

        if (!this.client.player.method_6120().isEmpty()) {
            ci.cancel();
        }
    }

    @Inject(method = "init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        if (!statusEffectFeature.getEnabled()) {
            return;
        }

        if (statusEffectFeature.getShowEffectsInInventory()) {
            return;
        }

        if (!this.client.player.method_6120().isEmpty()) {
            this.x -= 60;
        }
    }
}

