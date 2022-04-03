package com.deltaclient.common.bridge.option

@Suppress("INAPPLICABLE_JVM_NAME")
interface IKeyBindingBridge {
    @get:JvmName("bridge\$getKeyCode")
    val keyCode: Int

    @get:JvmName("bridge\$getKeyName")
    val name: String

    @get:JvmName("bridge\$isPressed")
    val pressed: Boolean
}