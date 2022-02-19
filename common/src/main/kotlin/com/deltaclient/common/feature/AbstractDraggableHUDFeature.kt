package com.deltaclient.common.feature

import com.deltaclient.common.Delta
import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import java.awt.Color

// TODO: Implement dragging of features

abstract class AbstractDraggableHUDFeature : IFeature {
    // X/Y indicating the top right
    var x = 0F
    var y = 0F

    var scale = 1F

    var width = 0F
    var height = 0F

    var drawBg = true
    var bgSize = 3F

    fun draw(event: RenderOverlayEvent) {
        if (drawBg) {
            drawBackground(event.matrices)
        }

        drawFeature(event)
    }

    private fun drawBackground(matrices: IMatrixStackBridge) {
        val bounds = calculateBounds(matrices)

        val x1 = x - bgSize
        val y1 = y - bgSize
        val x2 = x + bgSize + bounds.first
        val y2 = y + bgSize + bounds.second

        Delta.drawableHelper.fill(matrices, x1, y1, x2, y2, Color.BLACK.rgb)
    }

    // calc width/height of what gets drawn by #drawFeature(RenderOverlayEvent)
    protected abstract fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float>

    protected abstract fun drawFeature(event: RenderOverlayEvent)

    // Overriding these as text features shouldn't need them
    override fun onEnable() = Unit
    override fun onDisable() = Unit
}