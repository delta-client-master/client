package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.common.feature.text.cps.CPSTracker;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.util.collection.IntObjectStorage;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(KeyBinding.class)
public class KeyBindingMixin {
    @Shadow
    @Final
    private static IntObjectStorage KEY_MAP;

    /**
     * @author Lillian Armes
     */
    @Overwrite
    public static void onKeyPressed(int keyCode) {
        if (keyCode != 0) {
            if (keyCode < -1) {
                int button = keyCode + 100;
                if (button == 0) {
                    CPSTracker.INSTANCE.leftClick();
                } else if (button == 1) {
                    CPSTracker.INSTANCE.rightClick();
                }
            }

            KeyBinding keyBinding = (KeyBinding) KEY_MAP.get(keyCode);
            if (keyBinding != null) {
                ++keyBinding.timesPressed;
            }
        }
    }
}
