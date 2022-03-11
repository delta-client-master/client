package com.deltaclient.common.feature

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent

abstract class AbstractTextFeature(name: String, category: FeatureCategory) : AbstractDraggableHUDFeature(
    name,
    category
) {
    abstract val text: String

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> =
        mc.textRenderer.getWidth(text).toFloat() to mc.textRenderer.getHeight().toFloat()

    override fun drawFeature(event: RenderOverlayEvent) {
        mc.textRenderer.draw(event.matrices, text, x, y, textColor.rgb)
    }
}