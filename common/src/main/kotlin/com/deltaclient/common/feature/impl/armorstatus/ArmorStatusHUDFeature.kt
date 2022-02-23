package com.deltaclient.common.feature.impl.armorstatus

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureCategory
import java.awt.Color
import kotlin.math.roundToInt

class ArmorStatusHUDFeature : AbstractDraggableHUDFeature() {
    init {
        x = 200F
        y = 175F
    }

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> {
        var width = 0F
        var height = 0F

        for (stack in mc.player!!.inventory.armor.reversed()) {
            if (stack.item == null) {
                continue
            }

            val textWidth = mc.textRenderer.getWidth("${stack.maxDamage - stack.damage}/${stack.maxDamage}") + 17
            if (textWidth > width) {
                width = textWidth.toFloat()
            }

            height += 16
        }

        return Pair(width, height)
    }

    override fun drawFeature(event: RenderOverlayEvent) {
        var currY = y
        for (stack in mc.player!!.inventory.armor.reversed()) {
            if (stack.item == null) {
                continue
            }

            mc.itemRenderer.renderInGui(stack, x.roundToInt() - 1, currY.roundToInt())
            mc.textRenderer.draw(
                event.matrices,
                "${stack.maxDamage - stack.damage}/${stack.maxDamage}",
                x + 17,
                currY + 4,
                Color.white.rgb
            )

            currY += 16
        }
    }

    override val name: String = "armorstatus"
    override val category: FeatureCategory = FeatureCategory.HUD
}