package com.deltaclient.common.bridge.texture

import com.deltaclient.common.bridge.effect.IStatusEffectBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IStatusEffectSpriteManagerBridge {
    @JvmName("bridge\$getSprite")
    fun getSprite(effect: IStatusEffectBridge): ISpriteBridge
}