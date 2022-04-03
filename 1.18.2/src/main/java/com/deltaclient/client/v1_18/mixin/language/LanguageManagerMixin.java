package com.deltaclient.client.v1_18.mixin.language;

import com.deltaclient.common.bridge.language.ILanguageManagerBridge;
import net.minecraft.client.resource.language.LanguageManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin implements ILanguageManagerBridge {
    @Shadow
    private String currentLanguageCode;

    @NotNull
    @Override
    public String bridge$getCurrentLanguageCode() {
        return currentLanguageCode;
    }
}
