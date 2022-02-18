package com.deltaclient.common.feature

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.event.impl.RenderOverlayEvent
import java.awt.Color

abstract class AbstractTextFeature : AbstractDraggableHUDFeature() {
    abstract val text: String

    override fun draw(event: RenderOverlayEvent) {
        mc.textRenderer.draw(event.matrices, text, x, y, Color.WHITE.rgb)
    }
}