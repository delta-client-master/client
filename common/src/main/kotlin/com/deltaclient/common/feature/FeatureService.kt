package com.deltaclient.common.feature

import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.property.PropertyService
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

        register(FPSTextFeature())
        register(CPSTextFeature())
    }

    private fun register(feature: IFeature) {
        features.add(feature)
        PropertyService.register(feature)
    }
}