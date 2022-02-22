package com.deltaclient.common.bridge.texture

import com.deltaclient.common.bridge.util.IIdentifierBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface ISpriteBridge {
    @get:JvmName("bridge\$getAtlas")
    val atlas: IIdentifierBridge
}