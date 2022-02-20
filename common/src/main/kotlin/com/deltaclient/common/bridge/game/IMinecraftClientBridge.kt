package com.deltaclient.common.bridge.game

import com.deltaclient.common.bridge.lang.ILanguageManagerBridge
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge
import com.deltaclient.common.bridge.render.ITextRendererBridge
import com.deltaclient.common.bridge.session.ISessionBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IMinecraftClientBridge {
    @get:JvmName("bridge\$getClientPlayer")
    val player: IClientPlayerEntityBridge?

    @get:JvmName("bridge\$getLanguageManager")
    val languageManager: ILanguageManagerBridge

    @get:JvmName("bridge\$getSession")
    @set:JvmName("bridge\$setSession")
    var session: ISessionBridge?

    @get:JvmName("bridge\$getWindowHandle")
    val windowHandle: Long

    @get:JvmName("bridge\$getCurrentFps")
    val currentFps: Int

    @get:JvmName("bridge\$getTextRenderer")
    val textRenderer: ITextRendererBridge
}