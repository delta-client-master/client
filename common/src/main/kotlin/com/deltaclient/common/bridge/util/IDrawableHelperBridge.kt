package com.deltaclient.common.bridge.util

import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.texture.ISpriteBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IDrawableHelperBridge {
    @JvmName("bridge\$fill")
    fun fill(matrices: IMatrixStackBridge, x1: Float, y1: Float, x2: Float, y2: Float, color: Int)

    @JvmName("bridge\$drawTexture")
    fun drawTexture(
        matrices: IMatrixStackBridge,
        x: Int,
        y: Int,
        z: Int,
        u: Float,
        v: Float,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    )

    @JvmName("impl\$drawSprite")
    fun drawSprite(matrices: IMatrixStackBridge, x: Int, y: Int, width: Int, height: Int, sprite: ISpriteBridge)
}