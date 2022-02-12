package com.deltaclient.common.event

import com.deltaclient.common.event.annotation.Subscribe
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

internal class EventBusTest {
    @Test
    fun testSubscribe() {
        val latch = CountDownLatch(3)
        val sub = Sub(latch)

        EventBus.subscribe(sub)
        EventBus.subscribe<Int> {
            latch.countDown()
        }

        EventBus.post(1)
        latch.await(50, TimeUnit.MILLISECONDS)
    }

    class Sub(private val latch: CountDownLatch) {
        @Subscribe(Int::class)
        val fieldSub = Consumer<Int> {
            latch.countDown()
        }

        @Subscribe(Int::class)
        fun methodSub(int: Int) {
            latch.countDown()
        }
    }
}