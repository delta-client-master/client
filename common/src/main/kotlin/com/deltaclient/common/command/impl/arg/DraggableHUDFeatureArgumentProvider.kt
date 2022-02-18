package com.deltaclient.common.command.impl.arg

import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureService
import dev.lillian.bonk.core.argument.CommandArg
import dev.lillian.bonk.core.executor.CommandExecutor
import dev.lillian.bonk.core.provider.ArgumentProvider

class DraggableHUDFeatureArgumentProvider : ArgumentProvider<AbstractDraggableHUDFeature> {
    override fun provideSuggestions(executor: CommandExecutor<*>, input: String): MutableList<String>? = null

    override fun provide(arg: CommandArg, annotations: MutableList<out Annotation>): AbstractDraggableHUDFeature {
        return FeatureService.features.filterIsInstance<AbstractDraggableHUDFeature>().find { it.name == arg.get() }
            ?: throw IllegalArgumentException("${arg.get()} is not a draggable feature!")
    }

    override fun argumentDescription(): String = "feature"
}