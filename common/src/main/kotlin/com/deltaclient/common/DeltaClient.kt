package com.deltaclient.common

import com.deltaclient.common.bridge.client.IMinecraftClientBridge
import com.deltaclient.common.bridge.language.II18nBridge
import com.deltaclient.common.bridge.session.ISessionFactory
import com.deltaclient.common.bridge.util.IDrawableHelperBridge
import com.deltaclient.common.command.CommandRegistry
import com.deltaclient.common.command.impl.FeatureCommand
import com.deltaclient.common.command.impl.LoginCommand
import com.deltaclient.common.command.impl.arg.DraggableHUDFeatureArgumentProvider
import com.deltaclient.common.config.FeatureConfig
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureService
import com.deltaclient.common.i18n.I18nService
import com.deltaclient.common.model.GameVersion
import com.deltaclient.common.msa.MSAAuthService.doAuth
import com.deltaclient.common.socket.WebSocketClient
import com.deltaclient.common.util.ILWJGLDisplay
import com.mojang.authlib.exceptions.AuthenticationException
import net.arikia.dev.drpc.DiscordEventHandlers
import net.arikia.dev.drpc.DiscordRPC
import net.arikia.dev.drpc.DiscordRichPresence
import kotlin.properties.Delegates

object DeltaClient {
    val development = true // FIXME: Use launch arg or something

    var startTimestamp by Delegates.notNull<Long>()
        private set

    lateinit var gameVersion: GameVersion
        private set

    lateinit var mc: IMinecraftClientBridge
        private set

    lateinit var sessionFactory: ISessionFactory
        private set

    lateinit var lwjglDisplay: ILWJGLDisplay
        private set

    lateinit var drawableHelper: IDrawableHelperBridge
        private set

    lateinit var i18nBridge: II18nBridge
        private set

    fun onGameStart(
        gameVersion: GameVersion,
        mc: IMinecraftClientBridge,
        sessionFactory: ISessionFactory,
        lwjglDisplay: ILWJGLDisplay,
        drawableHelper: IDrawableHelperBridge,
        i18nBridge: II18nBridge
    ) {
        startTimestamp = System.currentTimeMillis()

        this.gameVersion = gameVersion
        this.mc = mc
        this.sessionFactory = sessionFactory
        this.lwjglDisplay = lwjglDisplay
        this.drawableHelper = drawableHelper
        this.i18nBridge = i18nBridge

        loginFromEnv()

        if (development) {
            val registry = CommandRegistry()
            registry.bind(AbstractDraggableHUDFeature::class.java).toProvider(DraggableHUDFeatureArgumentProvider())
            registry.register(FeatureCommand(), "feature", "f", "feat")
            registry.register(LoginCommand(), "login")
        }

        I18nService.load()
        lwjglDisplay.setTitle("${I18nService.translate("client_name")} - $gameVersion")

        // We only want features to load here, I think, could maybe load earlier, but it doesn't matter right now
        FeatureService

        FeatureConfig.load()
        val socket = WebSocketClient("ws://localhost:8080/auth")
        socket.connect()

        DiscordRPC.discordInitialize("944053798912024586", DiscordEventHandlers(), true)
        DiscordRPC.discordRegister("944053798912024586", "")

        val presence = DiscordRichPresence.Builder("Playing Minecraft $gameVersion")
            .setStartTimestamps(startTimestamp)
            .setBigImage("client_logo", "Delta Client")
            .setSmallImage("company_logo", "Delta Studios LLC")
            .build()

        DiscordRPC.discordUpdatePresence(presence)
    }

    fun onGameQuit() {
        FeatureConfig.save()
    }

    fun loginFromEnv() {
        if (System.getenv().containsKey("MC_CREDS")) {
            val creds = System.getenv("MC_CREDS").split(":").toTypedArray()
            val type = creds[0]
            try {
                val session = when (type) {
                    "mojang" -> sessionFactory.createMojangSession(creds[1], creds[2])

                    "msa" -> {
                        val refresh = if (creds.size == 1) {
                            null
                        } else {
                            creds[1]
                        }

                        doAuth(refresh, null)
                    }

                    else -> null
                }

                if (session != null) {
                    mc.session = session
                }
            } catch (e: AuthenticationException) {
                throw RuntimeException(e)
            }
        }
    }
}