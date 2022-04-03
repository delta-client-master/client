package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import org.junit.jupiter.api.Test
import java.util.function.Consumer

internal class EventFactoryTest {
    @Test
    fun testSubscriptions() {
        val subscriptions = EventFactory.getSubscriptions(ThreeSubscriptions())

        assert(subscriptions.size == 1)

        assert(subscriptions[String::class.java] != null)
        assert(subscriptions[Int::class.java] == null)

        assert(subscriptions[String::class.java]!!.size == 1)
    }

    class ThreeSubscriptions {
        @Subscribe(String::class)
        val consumerSub = Consumer<String> {}
    }
}