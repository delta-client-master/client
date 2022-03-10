package com.deltaclient.common.feature

import com.deltaclient.common.Delta
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.property.impl.BooleanProperty
import com.deltaclient.common.feature.property.impl.ColorProperty
import com.deltaclient.common.feature.property.impl.FloatProperty
import java.awt.Color

// TODO: Implement dragging of features

abstract class AbstractDraggableHUDFeature : IFeature {
    // X/Y indicating the top left
    var x by FloatProperty("x", 0F)
    var y by FloatProperty("y", 0F)

    var scale by FloatProperty("Scale", 1F)

    var width = 0F
    var height = 0F

    var textColor by ColorProperty("Text Color", Color.WHITE)

    var drawBg by BooleanProperty("Draw Background", true)
    var bgSize by FloatProperty("Background Size", 3F)
    var bgColor by ColorProperty("Background Color", Color.BLACK)

    fun draw(event: RenderOverlayEvent) {
        event.matrices.push()

        if (scale != 1F) {
            event.matrices.scale(scale, scale, 0F)
        }

        if (drawBg) {
            drawBackground(event.matrices)
        }

        drawFeature(event)

        event.matrices.pop()
    }

    private fun drawBackground(matrices: IMatrixStackBridge) {
        val bounds = calculateBounds(matrices)
        if (bounds.first == 0F || bounds.second == 0F) {
            return
        }

        val x1 = x - bgSize
        val y1 = y - bgSize
        val x2 = x + bgSize + bounds.first
        val y2 = y + bgSize + bounds.second

        Delta.drawableHelper.fill(matrices, x1, y1, x2, y2, bgColor.rgb)
    }

    // calc width/height of what gets drawn by #drawFeature(RenderOverlayEvent)
    protected abstract fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float>

    protected abstract fun drawFeature(event: RenderOverlayEvent)

    // Overriding these as text features shouldn't need them
    override fun onEnable() = Unit
    override fun onDisable() = Unit
}