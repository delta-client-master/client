package com.deltaclient.common.bridge.render

import com.deltaclient.common.bridge.item.IItemStackBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IItemRendererBridge {
    @JvmName("bridge\$renderInGui")
    fun renderInGui(stack: IItemStackBridge, x: Int, y: Int)
}