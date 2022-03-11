package com.deltaclient.common.feature.property

import com.deltaclient.common.feature.AbstractFeature

object PropertyService {
    private val featureProperties = hashMapOf<String, Set<AbstractProperty<Any>>>()

    fun register(feature: AbstractFeature) {
        val props = PropertyFactory.getProperties(feature)
        featureProperties[feature.name] = props
    }

    fun getProperties(feature: AbstractFeature) = featureProperties[feature.name]
}