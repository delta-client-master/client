package com.deltaclient.common.bridge.math

@Suppress("INAPPLICABLE_JVM_NAME")
interface IMatrixStackEntryBridge {
    @get:JvmName("bridge\$getPositionMatrix")
    val positionMatrix: IMatrix4fBridge
}