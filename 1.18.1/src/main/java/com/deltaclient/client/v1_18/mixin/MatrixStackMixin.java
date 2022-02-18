package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.render.IMatrixStackBridge;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MatrixStack.class)
public abstract class MatrixStackMixin implements IMatrixStackBridge {
    @Shadow
    public abstract void scale(float x, float y, float z);

    @Shadow
    public abstract void push();

    @Shadow
    public abstract void pop();

    @Override
    public void bridge$push() {
        push();
    }

    @Override
    public void bridge$pop() {
        pop();
    }

    @Override
    public void bridge$scale(float x, float y, float z) {
        scale(x, y, z);
    }
}
