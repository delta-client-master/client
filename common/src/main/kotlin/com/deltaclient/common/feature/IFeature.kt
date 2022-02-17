package com.deltaclient.common.feature

interface IFeature {
    val name: String

    val category: FeatureCategory

    fun onEnable()

    fun onDisable()
}