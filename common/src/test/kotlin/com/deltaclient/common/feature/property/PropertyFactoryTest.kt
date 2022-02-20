package com.deltaclient.common.feature.property

import com.deltaclient.common.feature.property.impl.BooleanProperty
import com.deltaclient.common.feature.property.impl.IntProperty
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class PropertyFactoryTest {
    open class A {
        val prop1 by BooleanProperty("Prop A", true)
    }

    class B : A() {
        val prop2 by IntProperty("Prop B", 1)
    }

    @Test
    fun getProperties() {
        val b = B()
        val props = PropertyFactory.getProperties(b)

        assertTrue { props.size == 2 }
    }
}