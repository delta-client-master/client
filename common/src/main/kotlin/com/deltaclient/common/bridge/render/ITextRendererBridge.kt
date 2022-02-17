package com.deltaclient.common.bridge.render

@Suppress("INAPPLICABLE_JVM_NAME")
interface ITextRendererBridge {
    @JvmName("bridge\$draw")
    fun draw(matrices: IMatrixStackBridge?, text: String, x: Float, y: Float, color: Int): Int

    @JvmName("bridge\$getWidth")
    fun getWidth(text: String): Int
}