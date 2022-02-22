package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.text.ITextBridge;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Text.class)
public interface TextMixin extends ITextBridge {
}
