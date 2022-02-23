package com.deltaclient.common.command

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge
import dev.lillian.bonk.core.executor.CommandExecutor

class ClientPlayerCommandExecutor(private val player: IClientPlayerEntityBridge) :
    CommandExecutor<IClientPlayerEntityBridge> {

    override fun getName(): String = player.getUsername()

    override fun sendMessage(message: String) {
        player.sendMessage(message)
    }

    override fun getInstance(): IClientPlayerEntityBridge = player
}