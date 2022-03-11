package com.deltaclient.common.feature

import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.impl.armorstatus.ArmorStatusHUDFeature
import com.deltaclient.common.feature.impl.statuseffect.StatusEffectHUDFeature
import com.deltaclient.common.feature.impl.text.FPSTextFeature
import com.deltaclient.common.feature.impl.text.cps.CPSTextFeature
import com.deltaclient.common.feature.property.PropertyService

// temp solution to get something working
object FeatureService {
    val features = hashSetOf<AbstractFeature>()

    init {
        EventBus.subscribe<RenderOverlayEvent> { event ->
            features.filterIsInstance<AbstractDraggableHUDFeature>().forEach {
                it.draw(event)
            }
        }

        register(FPSTextFeature())
        register(CPSTextFeature())
        register(StatusEffectHUDFeature())
        register(ArmorStatusHUDFeature())
    }

    private fun register(feature: AbstractFeature) {
        features.add(feature)
        PropertyService.register(feature)
    }
}