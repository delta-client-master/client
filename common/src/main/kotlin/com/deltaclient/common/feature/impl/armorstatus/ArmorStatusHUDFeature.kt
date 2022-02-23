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
        y = 150F
    }

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> {
        return Pair(0F, 0F)
    }

    override fun drawFeature(event: RenderOverlayEvent) {
        var currY = y
        for (stack in mc.player!!.inventory.armor.reversed()) {
            if (stack.item == null) {
                continue
            }

            mc.itemRenderer.renderInGui(stack, x.roundToInt(), currY.roundToInt())
            mc.textRenderer.draw(
                event.matrices,
                "${stack.maxDamage - stack.damage}/${stack.maxDamage}",
                x + 16,
                currY,
                Color.white.rgb
            )

            currY += 16
        }
    }

    override val name: String = "armorstatus"
    override val category: FeatureCategory = FeatureCategory.HUD
}