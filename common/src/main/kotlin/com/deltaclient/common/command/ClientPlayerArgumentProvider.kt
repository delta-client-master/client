package com.deltaclient.common.command

import com.deltaclient.common.bridge.entity.IClientPlayerEntityBridge
import dev.lillian.bonk.core.argument.CommandArg
import dev.lillian.bonk.core.executor.CommandExecutor
import dev.lillian.bonk.core.provider.ArgumentProvider

class ClientPlayerArgumentProvider : ArgumentProvider<IClientPlayerEntityBridge> {
    override fun consumesArgument(): Boolean = false

    override fun provideSuggestions(executor: CommandExecutor<*>, input: String): MutableList<String>? = null

    override fun provide(arg: CommandArg, annotations: MutableList<out Annotation>): IClientPlayerEntityBridge =
        arg.sender.instance as IClientPlayerEntityBridge

    override fun argumentDescription(): String = "sender"
}