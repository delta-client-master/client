package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import org.junit.jupiter.api.Test
import java.util.function.Consumer

internal class EventFactoryTest {
    @Test
    fun testSubscriptions() {
        val subscriptions = EventFactory.getSubscriptions(ThreeSubscriptions())

        // Two different classes
        assert(subscriptions.size == 2)

        assert(subscriptions[String::class.java] != null)
        assert(subscriptions[Int::class.java] != null)

        assert(subscriptions[String::class.java]!!.size == 2)
        assert(subscriptions[Int::class.java]!!.size == 1)
    }

    class ThreeSubscriptions {
        @Subscribe(String::class)
        val consumerSub = Consumer<String> {}

        @Subscribe(String::class)
        fun methodSub(string: String) {
        }

        @Subscribe(Int::class)
        fun methodSubTwo(int: Int) {
        }
    }
}