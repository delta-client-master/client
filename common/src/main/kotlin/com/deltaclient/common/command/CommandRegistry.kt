package com.deltaclient.common.command

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge
import com.deltaclient.common.event.EventBus
import com.deltaclient.common.event.impl.ChatMessageSendEvent
import dev.lillian.bonk.core.annotation.Sender
import dev.lillian.bonk.core.command.AbstractCommandRegistry
import dev.lillian.bonk.core.command.WrappedCommand
import java.util.concurrent.CompletableFuture

class CommandRegistry : AbstractCommandRegistry<BaseCommandContainer>() {
    init {
        EventBus.subscribe<ChatMessageSendEvent> {
            val player = mc.player

            var msg = it.message
            if (msg[0] != '.') {
                return@subscribe
            }

            it.cancelled = true

            msg = msg.substring(1)

            var args = msg.split(" ")
            val commandLabel = args[0]
            args = if (args.size > 1) {
                args.drop(1)
            } else {
                emptyList()
            }

            val executor = ClientPlayerCommandExecutor(player)

            var cmd = getCommands()[commandLabel]
            if (cmd == null) {
                getCommands().values.forEach { container ->
                    if (container.aliases.contains(commandLabel)) {
                        cmd = container
                        return@forEach
                    }
                }
            }

            if (cmd == null) {
                player.sendMessage("Invalid command")
                return@subscribe
            }

            executeCommand(executor, cmd!!, commandLabel, args.toTypedArray())
        }

        addDefaults()
    }

    override fun runAsync(runnable: Runnable) {
        CompletableFuture.runAsync(runnable)
    }

    override fun addDefaults() {
        bind(IClientPlayerEntityBridge::class.java).annotatedWith(Sender::class.java)
            .toProvider(ClientPlayerArgumentProvider())
    }

    override fun createContainer(
        `object`: Any, name: String, aliases: MutableSet<String>, commands: MutableMap<String, WrappedCommand>
    ): BaseCommandContainer = BaseCommandContainer(this, `object`, name, aliases, commands)
}