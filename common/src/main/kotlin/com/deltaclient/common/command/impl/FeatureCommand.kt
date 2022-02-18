package com.deltaclient.common.command.impl

import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import dev.lillian.bonk.core.annotation.Command
import dev.lillian.bonk.core.annotation.Sender

class FeatureCommand {
    @Command(name = "xy", desc = "Set coords for shit")
    fun coords(
        @Sender sender: IClientPlayerEntityBridge, feature: AbstractDraggableHUDFeature, x: Double, y: Double
    ) {
        feature.x = x.toFloat()
        feature.y = y.toFloat()

        sender.sendMessage("Set ${feature.name}'s X/Y to $x/$y!")
    }

    @Command(name = "scale", desc = "Set scale for shit")
    fun scale(
        @Sender sender: IClientPlayerEntityBridge, feature: AbstractDraggableHUDFeature, scale: Double
    ) {
        feature.scale = scale.toFloat()

        sender.sendMessage("Set ${feature.name}'s scale to $scale!")
    }
}