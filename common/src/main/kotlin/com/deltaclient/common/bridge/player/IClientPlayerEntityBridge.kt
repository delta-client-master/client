package com.deltaclient.common.bridge.player

import com.deltaclient.common.bridge.entity.ILivingEntityBridge
import java.util.*

@Suppress("INAPPLICABLE_JVM_NAME")
interface IClientPlayerEntityBridge : ILivingEntityBridge {
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