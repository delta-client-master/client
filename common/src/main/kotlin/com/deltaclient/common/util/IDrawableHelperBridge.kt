package com.deltaclient.common.util

import com.deltaclient.common.bridge.render.IMatrixStackBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IDrawableHelperBridge {
    @JvmName("bridge\$fill")
    fun fill(matrices: IMatrixStackBridge, x1: Float, y1: Float, x2: Float, y2: Float, color: Int)
}