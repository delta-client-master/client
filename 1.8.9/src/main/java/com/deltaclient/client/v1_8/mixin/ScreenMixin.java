package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.common.event.EventBus;
import com.deltaclient.common.event.impl.ChatMessageSendEvent;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ScreenMixin {
    @Inject(method = "sendMessage(Ljava/lang/String;Z)V", at = @At("HEAD"), cancellable = true)
    void handleChatMessage(String text, boolean toHud, CallbackInfo ci) {
        ChatMessageSendEvent event = EventBus.INSTANCE.post(new ChatMessageSendEvent(text));
        if (event.getCancelled()) {
            ci.cancel();
        }
    }
}
