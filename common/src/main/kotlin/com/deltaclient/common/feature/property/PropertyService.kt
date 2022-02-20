package com.deltaclient.common.feature.property

import com.deltaclient.common.feature.IFeature

object PropertyService {
    private val featureProperties = hashMapOf<String, Set<AbstractProperty<Any>>>()

    fun register(feature: IFeature) {
        val props = PropertyFactory.getProperties(feature)
        featureProperties[feature.name] = props
    }

    fun getProperties(feature: IFeature) = featureProperties[feature.name]
}