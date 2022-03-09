package com.deltaclient.client.v1_7.mixin.hud;

import com.deltaclient.client.v1_7.render.MatrixStack;
import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.RenderOverlayEvent;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Inject(method = "method_979", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getArmor(I)Lnet/minecraft/item/ItemStack;", shift = At.Shift.AFTER))
    private void render(float bl, boolean i, int j, int par4, CallbackInfo ci) {
        EventBus.INSTANCE.post(new RenderOverlayEvent(MatrixStack.INSTANCE, bl));
    }
}
