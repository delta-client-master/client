package com.deltaclient.common.event

import java.util.*
import java.util.function.Consumer

object EventBus {
    val subscriptions: MutableMap<Class<*>, MutableSet<Consumer<Any>>> = Collections.synchronizedMap(HashMap())

    private val cachedSubscriptions: MutableMap<Any, Map<Class<*>, MutableSet<Consumer<Any>>>> =
        Collections.synchronizedMap(HashMap())

    fun subscribe(parent: Any) {
        val handlers = cachedSubscriptions.getOrPut(parent) { EventFactory.getSubscriptions(parent) }
        subscriptions.putAll(handlers)
    }

    fun unsubscribe(parent: Any) {
        val handlers =
            cachedSubscriptions[parent] ?: return // Won't happen unless unsubscribed before subscribed
        handlers.forEach { (clazz, subs) ->
            subscriptions[clazz]?.removeAll(subs)
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T : Any> subscribe(consumer: Consumer<T>) {
        subscriptions.compute(T::class.java) { _, set ->
            val subscribers = set ?: hashSetOf()
            subscribers.add(consumer as Consumer<Any>)

            return@compute subscribers
        }
    }

    fun <T : Any> post(event: T): T {
        subscriptions[event::class.java]?.forEach { subscriber ->
            subscriber.accept(event)
        }

        return event
    }
}