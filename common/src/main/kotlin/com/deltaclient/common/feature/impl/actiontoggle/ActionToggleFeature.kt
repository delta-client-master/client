package com.deltaclient.common.feature.impl.actiontoggle

import com.deltaclient.common.DeltaClient.mc
import com.deltaclient.common.event.annotation.Subscribe
import com.deltaclient.common.event.impl.input.InputType
import com.deltaclient.common.event.impl.input.KeyboardInputEvent
import com.deltaclient.common.feature.AbstractFeature
import com.deltaclient.common.feature.FeatureCategory
import java.util.function.Consumer

class ActionToggleFeature : AbstractFeature("Action Toggle", FeatureCategory.ETC) {
    var shouldOverrideSprint = false
        private set

    @Subscribe(KeyboardInputEvent::class)
    private val onKey: Consumer<KeyboardInputEvent> = Consumer {
        if (it.type == InputType.PRESS && it.key == mc.options.keySprint.keyCode) {
            shouldOverrideSprint = !shouldOverrideSprint
        }
    }

    init {
        toggle()
    }

    override fun onEnable() = Unit
    override fun onDisable() = Unit
}