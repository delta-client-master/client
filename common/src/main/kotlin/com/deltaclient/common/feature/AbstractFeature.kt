package com.deltaclient.common.feature

import com.deltaclient.common.event.EventBus

abstract class AbstractFeature(val name: String, val category: FeatureCategory) {
    var enabled = false

    fun toggle() {
        enabled = !enabled

        if (enabled) {
            EventBus.subscribe(this)
            onEnable()
        } else {
            EventBus.unsubscribe(this)
            onDisable()
        }
    }

    abstract fun onEnable()

    abstract fun onDisable()
}