package com.deltaclient.client.v1_18.session

import com.deltaclient.common.Delta.mc
import com.deltaclient.common.bridge.session.ISessionBridge
import com.deltaclient.common.bridge.session.ISessionFactory
import com.deltaclient.common.ext.cast
import com.mojang.authlib.Agent
import com.mojang.authlib.exceptions.InvalidCredentialsException
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.Session
import java.util.*

object SessionFactory : ISessionFactory {
    private val authService = YggdrasilAuthenticationService((mc as MinecraftClient).networkProxy, "")

    override fun createMojangSession(email: String, password: String): ISessionBridge {
        val userAuth = authService.createUserAuthentication(Agent.MINECRAFT) as YggdrasilUserAuthentication
        userAuth.setUsername(email)
        userAuth.setPassword(password)
        userAuth.logIn()

        if (userAuth.isLoggedIn) {
            val profile = userAuth.selectedProfile
            return Session(
                profile.name,
                profile.id.toString(),
                userAuth.authenticatedToken,
                Optional.empty(),
                Optional.empty(),
                Session.AccountType.MOJANG
            ).cast()
        }

        throw InvalidCredentialsException()
    }
}
