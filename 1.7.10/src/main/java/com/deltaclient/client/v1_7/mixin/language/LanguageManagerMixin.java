package com.deltaclient.client.v1_7.mixin.language;

import com.deltaclient.common.bridge.language.ILanguageManagerBridge;
import net.minecraft.client.resource.language.LanguageManager;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LanguageManager.class)
public class LanguageManagerMixin implements ILanguageManagerBridge {
    @Shadow
    private String field_6652;

    @NotNull
    @Override
    public String bridge$getCurrentLanguageCode() {
        return field_6652;
    }
}
