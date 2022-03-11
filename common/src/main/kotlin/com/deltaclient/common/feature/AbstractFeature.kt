package com.deltaclient.common.feature

abstract class AbstractFeature(val name: String, val category: FeatureCategory) {
    var enabled = true

    abstract fun onEnable()

    abstract fun onDisable()
}