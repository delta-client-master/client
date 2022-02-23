package com.deltaclient.common.bridge.math

import com.deltaclient.common.bridge.math.IMatrix4fBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IMatrixStackEntryBridge {
    @get:JvmName("bridge\$getPositionMatrix")
    val positionMatrix: IMatrix4fBridge
}