package com.deltaclient.common.feature

import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.impl.actiontoggle.ActionToggleFeature
import com.deltaclient.common.feature.impl.armorstatus.ArmorStatusHUDFeature
import com.deltaclient.common.feature.impl.chat.ChatFeature
import com.deltaclient.common.feature.impl.keystrokes.KeystrokesFeature
import com.deltaclient.common.feature.impl.statuseffect.StatusEffectHUDFeature
import com.deltaclient.common.feature.impl.text.FPSTextFeature
import com.deltaclient.common.feature.impl.text.SaturationTextFeature
import com.deltaclient.common.feature.impl.text.cps.CPSTextFeature
import com.deltaclient.common.feature.property.PropertyService

@Suppress("UNCHECKED_CAST")
object FeatureService {
    val features = hashSetOf<AbstractFeature>()

    init {
        EventBus.subscribe<RenderOverlayEvent> { event ->
            features.filterIsInstance<AbstractDraggableHUDFeature>().filter { it.enabled }.forEach {
                it.draw(event)
            }
        }

        register(FPSTextFeature())
        register(CPSTextFeature())
        register(StatusEffectHUDFeature())
        register(ArmorStatusHUDFeature())
        register(SaturationTextFeature())
        register(ActionToggleFeature())
        register(ChatFeature())
        register(KeystrokesFeature())
    }

    private fun register(feature: AbstractFeature) {
        features.add(feature)
        PropertyService.register(feature)
    }

    fun <T : AbstractFeature> get(featureClass: Class<T>): T {
        return (features.find { it::class.java == featureClass } as T?)!!
    }

    fun <T : AbstractFeature> isToggled(featureClass: Class<T>): Boolean =
        features.find { it::class.java == featureClass }?.enabled ?: false
}