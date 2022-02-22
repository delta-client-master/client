package com.deltaclient.common.bridge.render

import com.deltaclient.common.bridge.text.ITextBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface ITextRendererBridge {
    @JvmName("bridge\$draw")
    fun draw(matrices: IMatrixStackBridge?, text: String, x: Float, y: Float, color: Int): Int

    @JvmName("bridge\$draw")
    fun draw(matrices: IMatrixStackBridge?, text: ITextBridge, x: Float, y: Float, color: Int): Int

    @JvmName("bridge\$getWidth")
    fun getWidth(text: String): Int

    @JvmName("bridge\$getWidth")
    fun getWidth(text: ITextBridge): Int

    @JvmName("impl\$getHeight")
    fun getHeight(): Int
}