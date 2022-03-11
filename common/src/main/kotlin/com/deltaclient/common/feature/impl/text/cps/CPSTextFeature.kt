package com.deltaclient.common.feature.impl.text.cps

import com.deltaclient.common.feature.AbstractTextFeature
import com.deltaclient.common.feature.FeatureCategory

class CPSTextFeature : AbstractTextFeature("CPS", FeatureCategory.HUD) {
    override val text
        get() = "CPS L = ${CPSTracker.getLeftCps()} : CPS R = ${CPSTracker.getRightCps()}"
}