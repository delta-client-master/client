package com.deltaclient.common.feature.text.cps

import java.util.*

object CPSTracker {
    private val leftClicks = Stack<Long>()
    private val rightClicks = Stack<Long>()

    fun leftClick() {
        leftClicks.add(System.currentTimeMillis())
    }

    fun rightClick() {
        rightClicks.add(System.currentTimeMillis())
    }

    fun getLeftCps(): Int = getCps(leftClicks)

    fun getRightCps(): Int = getCps(rightClicks)

    // FIXME: Precision on CPS returned
    private fun getCps(stack: Stack<Long>): Int {
        val currentTime = System.currentTimeMillis()
        stack.removeIf { currentTime > it + 1000 }

        return stack.size
    }
}