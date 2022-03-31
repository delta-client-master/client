package com.deltaclient.common.feature.property

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.util.function.Consumer
import kotlin.reflect.KProperty

abstract class AbstractProperty<T>(val name: String, default: T) {
    private var onValueChangeConsumer: Consumer<T>? = null

    var value: T = default
        set(value) {
            field = value
            onValueChangeConsumer?.accept(value)
        }

    fun onValueChange(consumer: Consumer<T>): AbstractProperty<T> {
        onValueChangeConsumer = consumer
        return this
    }

    abstract fun serialize(propertiesNode: ObjectNode)

    abstract fun deserialize(propertiesNode: JsonNode)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newVal: T) {
        value = newVal
    }
}