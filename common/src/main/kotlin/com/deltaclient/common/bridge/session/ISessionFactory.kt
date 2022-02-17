package com.deltaclient.common.bridge.session

import com.mojang.authlib.exceptions.AuthenticationException

interface ISessionFactory {
    @Throws(AuthenticationException::class)
    fun createMojangSession(email: String, password: String): ISessionBridge
}