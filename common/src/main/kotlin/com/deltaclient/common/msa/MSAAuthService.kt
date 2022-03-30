package com.deltaclient.common.msa

import com.deltaclient.common.Delta
import com.deltaclient.common.bridge.session.ISessionBridge
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import javafx.application.Application
import javafx.application.Platform
import javafx.concurrent.Worker
import javafx.scene.Scene
import javafx.scene.layout.VBox
import javafx.scene.web.WebView
import javafx.stage.Stage
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

object MSAAuthService {
    private val client = OkHttpClient()
    private val objectMapper = ObjectMapper()

    fun doAuth(refreshToken: String? = null, liveToken: String? = null): ISessionBridge? {
        val loginToken = liveToken ?: (refreshToken ?: manualAuth())
        println("loginToken = $loginToken")
        val liveLogin = loginLive(loginToken, refreshToken != null)
        println("liveLogin = $liveLogin")
        if (liveLogin != null) {
            val xboxLogin = loginXbox(liveLogin.first)
            println("xboxLogin = $xboxLogin")

            if (xboxLogin != null) {
                val xstsLogin = loginXsts(xboxLogin)
                println("xstsLogin = $xstsLogin")

                if (xstsLogin != null) {
                    val minecraftLogin = loginMinecraftServices(xstsLogin.first, xstsLogin.second)
                    println("minecraftLogin = $minecraftLogin")

                    if (minecraftLogin != null) {
                        val userDetails = getUserdetails(minecraftLogin)
                        println("userDetails = $userDetails")
                        if (userDetails != null) {
                            return Delta.sessionFactory.createMicrosoftSession(
                                userDetails.second, userDetails.first, minecraftLogin
                            )
                        }
                    }
                }
            }
        }

        return null
    }

    private fun manualAuth(): String {
        Application.launch(AuthApp::class.java)

        @Suppress("ControlFlowWithEmptyBody") while (AuthApp.token.isEmpty()) {
        }

        return AuthApp.token
    }

    /**
     * @return Pair containing access token and refresh token if successful, else null
     */
    private fun loginLive(token: String, refresh: Boolean): Pair<String, String>? {
        val requestBody = FormBody.Builder().addEncoded("client_id", "00000000402b5328")
            .addEncoded(if (refresh) "refresh_token" else "code", token)
            .addEncoded("grant_type", if (refresh) "refresh_token" else "authorization_code")
            .addEncoded("scope", "service::user.auth.xboxlive.com::MBI_SSL")
            .addEncoded("redirect_uri", "https://login.live.com/oauth20_desktop.srf").build()
        val request = Request.Builder().post(requestBody).url("https://login.live.com/oauth20_token.srf").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                println(it.body!!.string())
                return null
            }

            val body = it.body!!
            val root = objectMapper.readTree(body.bytes())
            return root["access_token"].asText() to root["refresh_token"].asText()
        }
    }

    /**
     * @return XboxLive access token if successful, else null
     */
    private fun loginXbox(accessToken: String): String? {
        val requestBody = ObjectNode(JsonNodeFactory.instance)

        requestBody.put("RelyingParty", "http://auth.xboxlive.com")
        requestBody.put("TokenType", "JWT")

        val propertiesProps = ObjectNode(JsonNodeFactory.instance)
        propertiesProps.put("AuthMethod", "RPS")
        propertiesProps.put("RpsTicket", accessToken)
        propertiesProps.put("SiteName", "user.auth.xboxlive.com")
        requestBody.set<ObjectNode>("Properties", propertiesProps)

        val request =
            Request.Builder().post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
                .url("https://user.auth.xboxlive.com/user/authenticate").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = objectMapper.readTree(body.bytes())

            return root["Token"].asText()
        }
    }

    /**
     * @return Pair containing access token and uhs if successful, else null
     */
    private fun loginXsts(token: String): Pair<String, String>? {
        val requestBody = ObjectNode(JsonNodeFactory.instance)
        val bodyProps = ObjectNode(JsonNodeFactory.instance)
        val tokensArray = JsonNodeFactory.instance.arrayNode()
        tokensArray.add(token)

        bodyProps.put("SandboxId", "RETAIL")
        bodyProps.set<ObjectNode>("UserTokens", tokensArray)

        requestBody.set<ObjectNode>("Properties", bodyProps)
        requestBody.put("TokenType", "JWT")
        requestBody.put("RelyingParty", "rp://api.minecraftservices.com/")

        val request =
            Request.Builder().post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
                .url("https://xsts.auth.xboxlive.com/xsts/authorize").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = objectMapper.readTree(body.bytes())

            return root["Token"].asText() to root["DisplayClaims"]["xui"][0]["uhs"].asText()
        }
    }

    /**
     * @return Minecraft access token if successful, else null
     */
    private fun loginMinecraftServices(token: String, uhs: String): String? {
        val requestBody = ObjectNode(JsonNodeFactory.instance)
        requestBody.put("identityToken", "XBL3.0 x=$uhs;$token")

        val request =
            Request.Builder().post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
                .url("https://api.minecraftservices.com/authentication/login_with_xbox").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = objectMapper.readTree(body.bytes())

            return root["access_token"].asText()
        }
    }

    /**
     * @return Pair containing player uuid and username if successful, else null
     */
    private fun getUserdetails(token: String): Pair<String, String>? {
        val request = Request.Builder().get().url("https://api.minecraftservices.com/minecraft/profile")
            .header("Authorization", "Bearer $token").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = objectMapper.readTree(body.bytes())

            return root["id"].asText() to root["name"].asText()
        }
    }

    class AuthApp : Application() {
        companion object {
            var token = ""
        }

        val serverPort = 6969

        override fun start(stage: Stage) {
            stage.title = "Delta Client MSA Auth"

            val view = WebView()
            val engine = view.engine

            engine.loadWorker.stateProperty().addListener { _, _, newValue ->
                if (newValue == Worker.State.SUCCEEDED) {
                    if (engine.location.contains("?code=")) {
                        val code = engine.location.substringAfter("?code=").substringBefore("&lc")
                        token = code
                        Platform.exit()
                    }
                }
            }

            engine.load("https://login.live.com/oauth20_authorize.srf?client_id=00000000402b5328&response_type=code&scope=service::user.auth.xboxlive.com::MBI_SSL&redirect_uri=https://login.live.com/oauth20_desktop.srf")
            val box = VBox(view)
            val scene = Scene(box, 680.0, 480.0)

            stage.scene = scene
            stage.show()
        }
    }
}