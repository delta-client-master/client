package com.deltaclient.common.command.impl

import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import dev.lillian.bonk.core.annotation.Command
import dev.lillian.bonk.core.annotation.Sender

class SetFeatureCoordsCommand {
    @Command(name = "", desc = "Set coords for shit")
    fun execute(
        @Sender sender: IClientPlayerEntityBridge, feature: AbstractDraggableHUDFeature, x: Double, y: Double
    ) {
        feature.x = x.toFloat()
        feature.y = y.toFloat()

        sender.sendMessage("Set ${feature.name}'s X/Y to $x/$y!")
    }
}