package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import java.util.function.Consumer
import kotlin.reflect.KClass

object EventFactory {
    fun getSubscriptions(parent: Any): Map<KClass<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<KClass<*>, MutableSet<Consumer<Any>>>()
        fun combine(extra: Map<KClass<*>, MutableSet<Consumer<Any>>>) {
            extra.forEach { (type, set) ->
                map.compute(type) { _, mapSet ->
                    val subscribers = mapSet ?: hashSetOf()
                    subscribers.addAll(set)

                    return@compute subscribers
                }
            }
        }

        combine(getFieldSubscriptions(parent::class, parent))
        combine(getMethodSubscriptions(parent::class, parent))

        return map
    }

    @Suppress("UNCHECKED_CAST")
    private fun getFieldSubscriptions(clazz: KClass<*>, parent: Any): Map<KClass<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<KClass<*>, MutableSet<Consumer<Any>>>()

        for (field in clazz.java.declaredFields) {
            field.trySetAccessible()

            if (!field.isAnnotationPresent(Subscribe::class.java) || field.type != Consumer::class.java) {
                continue
            }

            val annotation = field.getAnnotation(Subscribe::class.java)
            map.compute(annotation.value) { _, set ->
                val consumers = set ?: hashSetOf()
                consumers.add(field.get(parent) as Consumer<Any>)

                return@compute consumers
            }
        }

        return map
    }

    private fun getMethodSubscriptions(clazz: KClass<*>, parent: Any): Map<KClass<*>, MutableSet<Consumer<Any>>> {
        val map = hashMapOf<KClass<*>, MutableSet<Consumer<Any>>>()

        for (method in clazz.java.declaredMethods) {
            method.trySetAccessible()

            if (!method.isAnnotationPresent(Subscribe::class.java) || !method.returnType.isAssignableFrom(Void.TYPE) || method.parameterCount != 1) {
                continue
            }

            val annotation = method.getAnnotation(Subscribe::class.java)
            val eventType = annotation.value
            if (method.parameterTypes[0] != eventType.java) {
                continue
            }

            map.compute(annotation.value) { _, set ->
                val consumers = set ?: hashSetOf()
                consumers.add(EventInvoker(method, parent))

                return@compute consumers
            }
        }

        return map
    }
}