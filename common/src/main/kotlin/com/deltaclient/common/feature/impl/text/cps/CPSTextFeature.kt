package com.deltaclient.common.feature.impl.text.cps

import com.deltaclient.common.feature.AbstractTextFeature
import com.deltaclient.common.feature.FeatureCategory

class CPSTextFeature : AbstractTextFeature() {
    override val text
        get() = "CPS L = ${CPSTracker.getLeftCps()} : CPS R = ${CPSTracker.getRightCps()}"

    override val name = "CPS"

    override val category = FeatureCategory.HUD
}