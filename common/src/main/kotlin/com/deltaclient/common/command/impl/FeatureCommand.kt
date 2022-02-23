package com.deltaclient.common.command.impl

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.property.AbstractProperty
import com.deltaclient.common.feature.property.PropertyService
import com.deltaclient.common.feature.property.impl.BooleanProperty
import com.deltaclient.common.feature.property.impl.FloatProperty
import com.deltaclient.common.feature.property.impl.IntProperty
import dev.lillian.bonk.core.annotation.Command
import dev.lillian.bonk.core.annotation.Sender
import dev.lillian.bonk.core.exception.CommandExitMessage

class FeatureCommand {
    @Suppress("USELESS_CAST")
    @Command(name = "set", aliases = ["s"], desc = "Set properties")
    fun set(
        @Sender sender: IClientPlayerEntityBridge, feature: AbstractDraggableHUDFeature, propName: String, value: String
    ) {
        val prop = PropertyService.getProperties(feature)!!.find { it.name.replace(" ", "") == propName }
            ?.let { it as AbstractProperty<*> } ?: throw CommandExitMessage("Property cant be null")

        when (prop) {
            is IntProperty -> {
                prop.value = value.toInt()
            }
            is FloatProperty -> {
                prop.value = value.toFloat()
            }
            is BooleanProperty -> {
                prop.value = value.toBoolean()
            }
        }

        sender.sendMessage("Set ${feature.name}'s ${prop.name} to $value!")
    }
}