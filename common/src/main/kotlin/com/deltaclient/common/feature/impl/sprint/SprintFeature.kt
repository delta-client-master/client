package com.deltaclient.common.feature.impl.sprint

import com.deltaclient.common.DeltaClient.mc
import com.deltaclient.common.event.annotation.Subscribe
import com.deltaclient.common.event.impl.input.InputType
import com.deltaclient.common.event.impl.input.KeyboardInputEvent
import com.deltaclient.common.feature.AbstractFeature
import com.deltaclient.common.feature.FeatureCategory
import java.util.function.Consumer

class SprintFeature : AbstractFeature("Sprint", FeatureCategory.ETC) {
    var shouldOverrideSprint = false
        private set

    @Subscribe(KeyboardInputEvent::class)
    private val onKey: Consumer<KeyboardInputEvent> = Consumer {
        println(it.key)
        println(mc.options.keySprint.keyCode)
        if (it.type == InputType.PRESS && it.key == mc.options.keySprint.keyCode) {
            println("flipping sprint override")
            shouldOverrideSprint = !shouldOverrideSprint
        }
    }

    init {
        toggle()
    }

    override fun onEnable() = Unit
    override fun onDisable() = Unit
}