package com.deltaclient.client.v1_8.mixin;

import com.deltaclient.client.v1_8.DeltaPlatformImpl;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    // TODO: Put this in common, have common mixins for small things like this maybe
    @Inject(method = "setPixelFormat", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setTitle(Ljava/lang/String;)V", shift = At.Shift.AFTER))
    void setPixelFormat(CallbackInfo ci) {
        DeltaPlatformImpl.INSTANCE.getLWJGLUtil().setTitle("Title");
    }
}
