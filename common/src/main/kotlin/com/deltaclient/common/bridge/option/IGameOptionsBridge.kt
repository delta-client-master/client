package com.deltaclient.common.bridge.option

@Suppress("INAPPLICABLE_JVM_NAME")
interface IGameOptionsBridge {
    @get:JvmName("bridge\$getKeySprint")
    val keySprint: IKeyBindingBridge
}