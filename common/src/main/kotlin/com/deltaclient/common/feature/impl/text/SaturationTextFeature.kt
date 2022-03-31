package com.deltaclient.common.feature.impl.text

import com.deltaclient.common.DeltaClient.mc
import com.deltaclient.common.feature.AbstractTextFeature
import com.deltaclient.common.feature.FeatureCategory

class SaturationTextFeature : AbstractTextFeature("Saturation", FeatureCategory.HUD) {
    init {
        x = 250F
        y = 175F
    }

    override val text: String
        get() = (mc.player?.saturation ?: 0).toString()
}