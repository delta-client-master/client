package com.deltaclient.client.v1_18.mixin.text;

import com.deltaclient.common.bridge.text.ITranslatableTextBridge;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TranslatableText.class)
public class TranslatableTextMixin implements ITranslatableTextBridge {
}
