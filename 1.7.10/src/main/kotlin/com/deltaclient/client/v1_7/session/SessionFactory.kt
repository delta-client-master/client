package com.deltaclient.client.v1_7.session

import com.deltaclient.common.bridge.session.ISessionBridge
import com.deltaclient.common.bridge.session.ISessionFactory
import com.deltaclient.common.ext.cast
import com.mojang.authlib.Agent
import com.mojang.authlib.exceptions.InvalidCredentialsException
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.AccountType
import net.minecraft.client.util.Session

object SessionFactory : ISessionFactory {
    private val authService = YggdrasilAuthenticationService(MinecraftClient.getInstance().networkProxy, "")

    override fun createMojangSession(email: String, password: String): ISessionBridge {
        val userAuth = authService.createUserAuthentication(Agent.MINECRAFT) as YggdrasilUserAuthentication
        userAuth.setUsername(email)
        userAuth.setPassword(password)
        userAuth.logIn()

        if (userAuth.isLoggedIn) {
            val profile = userAuth.selectedProfile
            return Session(
                profile.name, profile.id.toString(), userAuth.authenticatedToken, AccountType.MOJANG.name
            ).cast()
        }

        throw InvalidCredentialsException()
    }

    override fun createMicrosoftSession(username: String, uuid: String, token: String): ISessionBridge {
        return Session(
            username,
            uuid,
            token,
            AccountType.MOJANG.name
        ).cast()
    }
}
