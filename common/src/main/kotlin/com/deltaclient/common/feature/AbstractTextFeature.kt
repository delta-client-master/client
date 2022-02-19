package com.deltaclient.common.feature

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import java.awt.Color

abstract class AbstractTextFeature : AbstractDraggableHUDFeature() {
    abstract val text: String

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> =
        mc.textRenderer.getWidth(text).toFloat() to mc.textRenderer.getHeight().toFloat()

    override fun drawFeature(event: RenderOverlayEvent) {
        event.matrices.push()

        if (scale != 1F) {
            event.matrices.scale(scale, scale, 0F)
        }

        mc.textRenderer.draw(event.matrices, text, x, y, Color.WHITE.rgb)

        event.matrices.pop()
    }
}