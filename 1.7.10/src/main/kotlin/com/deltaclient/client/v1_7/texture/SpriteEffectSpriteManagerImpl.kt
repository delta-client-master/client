package com.deltaclient.client.v1_7.texture

import com.deltaclient.common.bridge.effect.IStatusEffectBridge
import com.deltaclient.common.bridge.texture.ISpriteBridge
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge
import com.deltaclient.common.ext.cast
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.util.Identifier

object SpriteEffectSpriteManagerImpl : IStatusEffectSpriteManagerBridge {
    private val sprites = mutableMapOf<Int, CustomSprite>()

    init {
        val id = Identifier("minecraft", "textures/gui/container/inventory.png")

        for (effect in StatusEffect.STATUS_EFFECTS) {
            if (effect == null) {
                continue
            }

            val pos = effect.method_2444()
            val u = pos % 8 * 18
            val v = 198 + pos / 8 * 18
            sprites[effect.id] = CustomSprite(id.cast(), u, v, 18, 18)
        }
    }

    override fun getSprite(effect: IStatusEffectBridge): ISpriteBridge {
        return sprites[effect.cast<StatusEffect>().id]!!.cast()
    }
}