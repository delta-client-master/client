package com.deltaclient.common.command

import dev.lillian.bonk.core.command.AbstractCommandRegistry
import dev.lillian.bonk.core.command.CommandContainer
import dev.lillian.bonk.core.command.WrappedCommand

class BaseCommandContainer(
    commandService: AbstractCommandRegistry<*>?,
    `object`: Any?,
    name: String?,
    aliases: MutableSet<String>?,
    commands: MutableMap<String, WrappedCommand>?
) : CommandContainer(
    commandService, `object`, name, aliases, commands
)