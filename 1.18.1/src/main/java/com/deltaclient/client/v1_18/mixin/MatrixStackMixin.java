package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MatrixStack.class)
public class MatrixStackMixin implements IMatrixStackBridge {
}
