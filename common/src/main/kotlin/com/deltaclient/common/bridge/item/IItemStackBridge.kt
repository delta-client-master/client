package com.deltaclient.common.bridge.item

@Suppress("INAPPLICABLE_JVM_NAME")
interface IItemStackBridge {
    @get:JvmName("bridge\$getItem")
    val item: IItemBridge?

    @get:JvmName("bridge\$getDamage")
    val damage: Int

    @get:JvmName("bridge\$getMaxDamage")
    val maxDamage: Int
}