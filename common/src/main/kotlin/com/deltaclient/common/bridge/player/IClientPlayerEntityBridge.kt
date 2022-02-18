package com.deltaclient.common.bridge.player

import java.util.*

@Suppress("INAPPLICABLE_JVM_NAME")
interface IClientPlayerEntityBridge {
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