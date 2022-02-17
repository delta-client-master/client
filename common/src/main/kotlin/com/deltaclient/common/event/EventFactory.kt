package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import java.util.function.Consumer

object EventFactory {
    fun getSubscriptions(parent: Any): Map<Class<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<Class<*>, MutableSet<Consumer<Any>>>()
        fun combine(extra: Map<Class<*>, MutableSet<Consumer<Any>>>) {
            extra.forEach { (type, set) ->
                map.compute(type) { _, mapSet ->
                    val subscribers = mapSet ?: hashSetOf()
                    subscribers.addAll(set)

                    return@compute subscribers
                }
            }
        }

        combine(getFieldSubscriptions(parent::class.java, parent))
        combine(getMethodSubscriptions(parent::class.java, parent))

        return map
    }

    @Suppress("UNCHECKED_CAST")
    private fun getFieldSubscriptions(clazz: Class<*>, parent: Any): Map<Class<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<Class<*>, MutableSet<Consumer<Any>>>()

        for (field in clazz.declaredFields) {
            field.trySetAccessible()

            if (!field.isAnnotationPresent(Subscribe::class.java) || field.type != Consumer::class.java) {
                continue
            }

            val annotation = field.getAnnotation(Subscribe::class.java)
            map.compute(annotation.value.java) { _, set ->
                val consumers = set ?: hashSetOf()
                consumers.add(field.get(parent) as Consumer<Any>)

                return@compute consumers
            }
        }

        return map
    }

    private fun getMethodSubscriptions(clazz: Class<*>, parent: Any): Map<Class<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<Class<*>, MutableSet<Consumer<Any>>>()

        for (method in clazz.declaredMethods) {
            method.trySetAccessible()

            if (!method.isAnnotationPresent(Subscribe::class.java) || !method.returnType.isAssignableFrom(Void.TYPE) || method.parameterCount != 1) {
                continue
            }

            val annotation = method.getAnnotation(Subscribe::class.java)
            val eventType = annotation.value
            if (method.parameterTypes[0] != eventType.java) {
                continue
            }

            map.compute(annotation.value.java) { _, set ->
                val consumers = set ?: hashSetOf()
                consumers.add(EventInvoker(method, parent))

                return@compute consumers
            }
        }

        return map
    }
}