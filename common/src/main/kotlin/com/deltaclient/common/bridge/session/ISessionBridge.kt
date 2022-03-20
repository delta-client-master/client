package com.deltaclient.common.bridge.session

import com.mojang.authlib.GameProfile

@Suppress("INAPPLICABLE_JVM_NAME")
interface ISessionBridge {
    @get:JvmName("bridge\$getUsername")
    val username: String

    @get:JvmName("bridge\$getProfile")
    val profile: GameProfile

    @get:JvmName("bridge\$getAccessToken")
    val accessToken: String
}