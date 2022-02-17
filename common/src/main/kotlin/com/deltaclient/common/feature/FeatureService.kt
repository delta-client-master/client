package com.deltaclient.common.feature

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.text.FPSTextFeature
import java.awt.Color

// temp solution to get something working
object FeatureService {
    private val features = hashSetOf<IFeature>()

    init {
        EventBus.subscribe<RenderOverlayEvent> { event ->
            var x = 2F
            features.filterIsInstance<AbstractTextFeature>().forEach {
                mc.textRenderer.draw(event.matrices, it.text, x, 4F, Color.WHITE.rgb)
                x += mc.textRenderer.getWidth(it.text) + 2
            }
        }

        features.add(FPSTextFeature())
    }
}