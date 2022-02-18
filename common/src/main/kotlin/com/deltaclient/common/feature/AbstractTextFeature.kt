package com.deltaclient.common.feature

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.event.impl.RenderOverlayEvent
import java.awt.Color

abstract class AbstractTextFeature : AbstractDraggableHUDFeature() {
    abstract val text: String

    override fun draw(event: RenderOverlayEvent) {
        event.matrices.push()

        if (scale != 1F) {
            event.matrices.scale(scale, scale, 0F)
        }

        mc.textRenderer.draw(event.matrices, text, x, y, Color.WHITE.rgb)

        event.matrices.pop()
    }
}