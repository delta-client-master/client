package com.deltaclient.common.feature

import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.text.FPSTextFeature
import com.deltaclient.common.feature.text.cps.CPSTextFeature

// temp solution to get something working
object FeatureService {
    val features = hashSetOf<IFeature>()

    init {
        EventBus.subscribe<RenderOverlayEvent> { event ->
            features.filterIsInstance<AbstractDraggableHUDFeature>().forEach {
                it.draw(event)
            }
        }

        features.add(FPSTextFeature())
        features.add(CPSTextFeature())
    }
}