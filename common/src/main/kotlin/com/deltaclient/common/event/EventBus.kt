package com.deltaclient.common.event

import java.util.*
import java.util.function.Consumer
import kotlin.reflect.KClass

object EventBus {
    val subscriptions: MutableMap<KClass<*>, MutableSet<Consumer<Any>>> = Collections.synchronizedMap(hashMapOf())

    fun subscribe(parent: Any) {
        val handlers = EventFactory.getSubscriptions(parent)
        subscriptions.putAll(handlers)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any> subscribe(consumer: Consumer<T>) {
        subscriptions.compute(T::class) { _, set ->
            val subscribers = set ?: hashSetOf()
            subscribers.add(consumer as Consumer<Any>)

            return@compute set
        }
    }

    fun <T : Any> post(event: T): T {
        subscriptions[event::class]?.forEach { subscriber ->
            subscriber.accept(event)
        }

        return event
    }
}