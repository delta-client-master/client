package com.deltaclient.common.bridge.entity.player

@Suppress("INAPPLICABLE_JVM_NAME")
interface IPlayerEntityBridge {
    @get:JvmName("bridge\$getInventory")
    val inventory: IPlayerInventoryBridge
}