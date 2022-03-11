package com.deltaclient.common.feature.impl.statuseffect

import com.deltaclient.common.Delta
import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureCategory

class StatusEffectHUDFeature : AbstractDraggableHUDFeature("Status Effect", FeatureCategory.HUD) {
    init {
        x = 50F
        y = 50F
    }

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> {
        var width = 0F
        var height = 0F

        for (effect in mc.player?.getStatusEffects()!!) {
            val effectName = effect.type.name ?: continue
            val textWidth = mc.textRenderer.getWidth(effectName)
            if (textWidth > width) {
                width = 18F + textWidth
            }

            height += 18
        }

        return Pair(width, height)
    }

    override fun drawFeature(event: RenderOverlayEvent) {
        var currY = y.toInt()
        val currX = x.toInt()

        for (effect in mc.player!!.getStatusEffects()) {
            val effectName = effect.type.name ?: continue

            val sprite = mc.statusEffectSpriteManager.getSprite(effect.type)
            Delta.drawableHelper.drawSprite(event.matrices, currX - 2, currY - 1, 18, 18, sprite)
            mc.textRenderer.draw(event.matrices, effectName, currX + 18F, currY + 4F, textColor.rgb)
            currY += 18
        }
    }
}