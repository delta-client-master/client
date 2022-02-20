package com.deltaclient.common.feature.property

import kotlin.reflect.KProperty

abstract class AbstractProperty<T>(val name: String) {
    abstract var value: T

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = value

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newVal: T) {
        value = newVal
    }
}