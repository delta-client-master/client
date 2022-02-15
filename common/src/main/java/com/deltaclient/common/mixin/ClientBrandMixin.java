package com.deltaclient.common.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(targets = "net.minecraft.client.ClientBrandRetriever")
public class ClientBrandMixin {
    /**
     * @author Lillian Armes
     */
    @net.minecraft.obfuscate.DontObfuscate
    @Overwrite
    public static String getClientModName() {
        return "Delta";
    }
}
