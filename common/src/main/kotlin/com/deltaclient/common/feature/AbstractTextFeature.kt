package com.deltaclient.common.feature

abstract class AbstractTextFeature : IFeature {
    abstract val text: String

    // Overriding these as text features shouldn't need them
    override fun onEnable() = Unit
    override fun onDisable() = Unit
}