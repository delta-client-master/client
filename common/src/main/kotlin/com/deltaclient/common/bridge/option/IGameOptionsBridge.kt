package com.deltaclient.common.bridge.option

@Suppress("INAPPLICABLE_JVM_NAME")
interface IGameOptionsBridge {
    @get:JvmName("bridge\$getKeyForward")
    val keyForward: IKeyBindingBridge

    @get:JvmName("bridge\$getKeyBack")
    val keyBack: IKeyBindingBridge

    @get:JvmName("bridge\$getKeyLeft")
    val keyLeft: IKeyBindingBridge

    @get:JvmName("bridge\$getKeyRight")
    val keyRight: IKeyBindingBridge

    @get:JvmName("bridge\$getKeySprint")
    val keySprint: IKeyBindingBridge

    @get:JvmName("bridge\$getKeySneak")
    val keySneak: IKeyBindingBridge
}