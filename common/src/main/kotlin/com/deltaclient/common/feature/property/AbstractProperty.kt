package com.deltaclient.common.feature.property

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import kotlin.reflect.KProperty

abstract class AbstractProperty<T>(val name: String) {
    abstract var value: T

    abstract fun serialize(propertiesNode: ObjectNode)

    abstract fun deserialize(propertiesNode: JsonNode)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newVal: T) {
        value = newVal
    }
}