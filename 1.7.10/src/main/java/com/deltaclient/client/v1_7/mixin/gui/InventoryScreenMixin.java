package com.deltaclient.client.v1_7.mixin.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.screen.ScreenHandler;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.Iterator;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends HandledScreen {
    public InventoryScreenMixin(ScreenHandler screenHandler) {
        super(screenHandler);
    }

    /**
     * @author
     */
    @Overwrite
    private void drawStatusEffects() {
        int var1 = this.x - 124;
        int var2 = this.y;
        Collection var4 = this.client.field_3805.method_6120();
        if (!var4.isEmpty()) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(2896);
            int var5 = 33;
            if (var4.size() > 5) {
                var5 = 132 / (var4.size() - 1);
            }

            for(Iterator var6 = this.client.field_3805.method_6120().iterator(); var6.hasNext(); var2 += var5) {
                StatusEffectInstance var7 = (StatusEffectInstance)var6.next();
                StatusEffect var8 = StatusEffect.STATUS_EFFECTS[var7.getEffectId()];
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.client.getTextureManager().bindTexture(INVENTORY_TEXTURE);
                this.drawTexture(var1, var2, 0, 166, 140, 32);
                if (var8.method_2443()) {
                    int var9 = var8.method_2444();
                    this.drawTexture(var1 + 6, var2 + 7, var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
                }

                String var11 = I18n.translate(var8.getTranslationKey());
                if (var7.getAmplifier() == 1) {
                    var11 = var11 + " " + I18n.translate("enchantment.level.2");
                } else if (var7.getAmplifier() == 2) {
                    var11 = var11 + " " + I18n.translate("enchantment.level.3");
                } else if (var7.getAmplifier() == 3) {
                    var11 = var11 + " " + I18n.translate("enchantment.level.4");
                }

                this.textRenderer.method_956(var11, var1 + 10 + 18, var2 + 6, 16777215);
                String var10 = StatusEffect.method_2436(var7);
                this.textRenderer.method_956(var10, var1 + 10 + 18, var2 + 6 + 10, 8355711);
            }

        }
    }
}
