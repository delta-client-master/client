package com.deltaclient.common.command.impl

import com.deltaclient.common.Delta
import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge
import dev.lillian.bonk.core.annotation.Command
import dev.lillian.bonk.core.annotation.Sender

class LoginCommand {
    @Command(name = "", desc = "")
    fun execute(@Sender sender: IClientPlayerEntityBridge) {
        Delta.loginFromEnv()
        sender.sendMessage("Done")
    }
}