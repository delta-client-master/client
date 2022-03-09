package com.deltaclient.common.bridge.effect

@Suppress("INAPPLICABLE_JVM_NAME")
interface IStatusEffectInstanceBridge {
    @get:JvmName("bridge\$getType")
    val type: IStatusEffectBridge

    @get:JvmName("bridge\$showIcon")
    @set:JvmName("bridge\$showIcon")
    var showIcon: Boolean
}