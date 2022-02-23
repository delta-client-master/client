package com.deltaclient.common.bridge.entity.player

import com.deltaclient.common.bridge.item.IItemStackBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface IPlayerInventoryBridge {
    @get:JvmName("bridge\$getArmor")
    val armor: List<IItemStackBridge>
}