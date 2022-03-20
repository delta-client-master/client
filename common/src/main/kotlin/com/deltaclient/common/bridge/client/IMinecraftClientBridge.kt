package com.deltaclient.common.bridge.client

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge
import com.deltaclient.common.bridge.font.ITextRendererBridge
import com.deltaclient.common.bridge.language.ILanguageManagerBridge
import com.deltaclient.common.bridge.render.IItemRendererBridge
import com.deltaclient.common.bridge.session.ISessionBridge
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge
import com.mojang.authlib.minecraft.MinecraftSessionService

@Suppress("INAPPLICABLE_JVM_NAME")
interface IMinecraftClientBridge {
    @get:JvmName("bridge\$getClientPlayer")
    val player: IClientPlayerEntityBridge?

    @get:JvmName("bridge\$getLanguageManager")
    val languageManager: ILanguageManagerBridge

    @get:JvmName("bridge\$getSession")
    @set:JvmName("bridge\$setSession")
    var session: ISessionBridge

    @get:JvmName("bridge\$getWindowHandle")
    val windowHandle: Long

    @get:JvmName("bridge\$getCurrentFps")
    val currentFps: Int

    @get:JvmName("bridge\$getTextRenderer")
    val textRenderer: ITextRendererBridge

    @get:JvmName("bridge\$getStatusEffectSpriteManager")
    val statusEffectSpriteManager: IStatusEffectSpriteManagerBridge

    @get:JvmName("bridge\$getItemRenderer")
    val itemRenderer: IItemRendererBridge

    @get:JvmName("bridge\$getSessionService")
    val sessionService: MinecraftSessionService
}