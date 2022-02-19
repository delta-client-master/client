package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.math.IMatrix4fBridge;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Matrix4f.class)
public class Matrix4fMixin implements IMatrix4fBridge {
}
