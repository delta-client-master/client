package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import kotlin.test.assertTrue

internal class EventBusTest {
    @Test
    fun testSubscribe() {
        val latch = CountDownLatch(3)
        val sub = Sub(latch)

        EventBus.subscribe(sub)
        EventBus.subscribe<String> {
            latch.countDown()
        }

        EventBus.post("Hello World")
        assertTrue { latch.await(50, TimeUnit.MILLISECONDS) }
    }

    class Sub(private val latch: CountDownLatch) {
        @Subscribe(String::class)
        val fieldSub = Consumer<String> {
            latch.countDown()
        }

        @Subscribe(String::class)
        fun methodSub(int: String) {
            latch.countDown()
        }
    }
}