package com.deltaclient.client.v1_7.mixin.gui;

import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.ChatMessageSendEvent;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(method = "method_6738", at = @At("HEAD"), cancellable = true)
    private void handleChatMessage(String par1, CallbackInfo ci) {
        ChatMessageSendEvent event = EventBus.INSTANCE.post(new ChatMessageSendEvent(par1));
        if (event.getCancelled()) {
            ci.cancel();
        }
    }
}
