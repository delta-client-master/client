package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.common.bridge.util.IIdentifierBridge;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Identifier.class)
public class IdentifierMixin implements IIdentifierBridge {
}
