package com.deltaclient.common.feature.impl.text

import com.deltaclient.common.Delta
import com.deltaclient.common.feature.AbstractTextFeature
import com.deltaclient.common.feature.FeatureCategory

class FPSTextFeature : AbstractTextFeature("FPS", FeatureCategory.HUD) {
    override val text: String
        get() = "FPS: ${Delta.mc.currentFps}"
}