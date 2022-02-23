package com.deltaclient.common.bridge.entity

import com.deltaclient.common.bridge.entity.player.IPlayerEntityBridge
import java.util.*

@Suppress("INAPPLICABLE_JVM_NAME")
interface IClientPlayerEntityBridge : ILivingEntityBridge, IPlayerEntityBridge {
    @JvmName("bridge\$getUsername")
    fun getUsername(): String

    @JvmName("bridge\$getUUID")
    fun getUUID(): UUID

    /**
     * Adds a message into their chat box
     */
    @JvmName("impl\$sendMessage")
    fun sendMessage(message: String)
}