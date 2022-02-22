package com.deltaclient.common.feature.statuseffect

import com.deltaclient.common.Delta
import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureCategory
import java.awt.Color

class StatusEffectHUDFeature : AbstractDraggableHUDFeature() {
    init {
        x = 50F
        y = 50F
    }

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> {
        var width = 0F
        var height = 0F

        for (effect in mc.player!!.getStatusEffects()) {
            val textWidth = mc.textRenderer.getWidth(effect.type.name)
            if (textWidth > width) {
                width = 18F + textWidth
            }

            height += 16
        }

        return Pair(width, height)
    }

    override fun drawFeature(event: RenderOverlayEvent) {
        var currY = y.toInt()
        val currX = x.toInt()

        for (effect in mc.player!!.getStatusEffects()) {
            val sprite = mc.statusEffectSpriteManager.getSprite(effect.type)
            Delta.drawableHelper.drawSprite(event.matrices, currX, currY, 18, 18, sprite)
            mc.textRenderer.draw(event.matrices, effect.type.name, currX + 18F, currY + 3F, Color.WHITE.rgb)
            currY += 18
        }
    }

    override val name = "Status Effect"
    override val category = FeatureCategory.HUD
}