package com.deltaclient.client.v1_18.mixin.math;

import com.deltaclient.common.bridge.math.IMatrix4fBridge;
import com.deltaclient.common.bridge.math.IMatrixStackEntryBridge;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MatrixStack.Entry.class)
public class MatrixStackEntryMixin implements IMatrixStackEntryBridge {
    @Shadow
    @Final
    Matrix4f positionMatrix;

    @NotNull
    @Override
    public IMatrix4fBridge bridge$getPositionMatrix() {
        return (IMatrix4fBridge) positionMatrix;
    }
}
