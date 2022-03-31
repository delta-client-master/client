package com.deltaclient.client.v1_18.mixin.input;

import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.feature.impl.actiontoggle.ActionToggleFeature;
import com.deltaclient.common.util.BasicLazy;
import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin {
    private final BasicLazy<ActionToggleFeature> actionToggleFeature = new BasicLazy<>(() -> FeatureService.INSTANCE.get(ActionToggleFeature.class));

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z", ordinal = 5))
    private boolean tick(KeyBinding instance) {
        return (actionToggleFeature.get().getEnabled() && actionToggleFeature.get().getShouldOverrideSneak()) || instance.isPressed();
    }
}
