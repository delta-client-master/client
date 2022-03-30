package com.deltaclient.common.feature.impl.chat

import com.deltaclient.common.feature.AbstractFeature
import com.deltaclient.common.feature.FeatureCategory
import com.deltaclient.common.feature.property.impl.BooleanProperty

class ChatFeature : AbstractFeature("Chat", FeatureCategory.HUD) {
    val drawBackground by BooleanProperty("Draw Background", false)

    init {
        enabled = true
    }

    override fun onEnable() {
    }

    override fun onDisable() {
    }
}