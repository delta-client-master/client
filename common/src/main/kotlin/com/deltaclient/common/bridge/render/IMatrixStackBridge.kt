package com.deltaclient.common.bridge.render

@Suppress("INAPPLICABLE_JVM_NAME")
interface IMatrixStackBridge {
    @JvmName("bridge\$push")
    fun push()

    @JvmName("bridge\$pop")
    fun pop()

    @JvmName("bridge\$scale")
    fun scale(x: Float, y: Float, z: Float)

    @JvmName("bridge\$peek")
    fun peek(): IMatrixStackEntryBridge
}