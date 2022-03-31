package com.deltaclient.common.feature.impl.actiontoggle

import com.deltaclient.common.DeltaClient.mc
import com.deltaclient.common.event.annotation.Subscribe
import com.deltaclient.common.event.impl.input.InputType
import com.deltaclient.common.event.impl.input.KeyboardInputEvent
import com.deltaclient.common.feature.AbstractFeature
import com.deltaclient.common.feature.FeatureCategory
import com.deltaclient.common.feature.property.impl.BooleanProperty
import java.util.function.Consumer

class ActionToggleFeature : AbstractFeature("Action Toggle", FeatureCategory.ETC) {
    var shouldOverrideSprint = false
        private set

    private val sprint by BooleanProperty("Sprint", true).onValueChange {
        if (!it) {
            shouldOverrideSprint = false
        }
    }

    @Subscribe(KeyboardInputEvent::class)
    private val onKey: Consumer<KeyboardInputEvent> = Consumer {
        if (sprint && it.type == InputType.PRESS && it.key == mc.options.keySprint.keyCode) {
            shouldOverrideSprint = !shouldOverrideSprint
        }
    }

    init {
        toggle()
    }

    override fun onEnable() = Unit
    override fun onDisable() = Unit
}