package com.deltaclient.common.bridge.effect

import com.deltaclient.common.bridge.text.ITranslatableTextBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IStatusEffectBridge {
    @get:JvmName("bridge\$getName")
    val name: ITranslatableTextBridge
}