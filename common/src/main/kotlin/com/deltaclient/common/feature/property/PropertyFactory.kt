package com.deltaclient.common.feature.property

internal object PropertyFactory {
    @Suppress("UNCHECKED_CAST")
    fun getProperties(
        parent: Any, clazz: Class<*> = parent.javaClass, existing: MutableSet<AbstractProperty<Any>> = linkedSetOf()
    ): Set<AbstractProperty<Any>> {
        for (field in clazz.declaredFields) {
            if (!AbstractProperty::class.java.isAssignableFrom(field.type)) {
                continue
            }

            field.trySetAccessible()

            val property = field[parent]
            existing.add(property as AbstractProperty<Any>)
        }

        return if (Object::class.java == clazz.superclass) existing else getProperties(
            parent, clazz.superclass, existing
        )
    }
}