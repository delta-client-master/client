package com.deltaclient.common.msa

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
    private val appID = "3488dae4-fde2-4928-a322-fa3bcef7e45c"
    private val appSecret = "~n87Q~AnTeKd2rAekt6D3YNr0WnDD6q0GNhtp"

    fun doAuth(refreshToken: String?): String {
        val loginToken = refreshToken ?: manualAuth()
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
                    }
                }
            }
        }

        return "!!"
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
        val client = OkHttpClient()
        val requestBody = FormBody.Builder().addEncoded("client_id", appID)
            .addEncoded(if (refresh) "refresh_token" else "code", token)
            .addEncoded("grant_type", if (refresh) "refresh_token" else "authorization_code")
            .addEncoded("redirect_uri", "http://localhost:6969/auth")
            .addEncoded("scope", "XboxLive.signin offline_access").addEncoded("client_secret", appSecret).build()
        val request = Request.Builder().post(requestBody).url("https://login.live.com/oauth20_token.srf").build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = ObjectMapper().readTree(body.bytes())
            return root["access_token"].asText() to root["refresh_token"].asText()
        }
    }

    /**
     * @return XboxLive access token if successful, else null
     */
    private fun loginXbox(accessToken: String): String? {
        val requestBody = ObjectNode(JsonNodeFactory.instance)
        val bodyProps = ObjectNode(JsonNodeFactory.instance)
        bodyProps.put("AuthMethod", "RPS")
        bodyProps.put("RpsTicket", "d=$accessToken")
        bodyProps.put("SiteName", "user.auth.xboxlive.com")

        requestBody.set<ObjectNode>("Properties", bodyProps)
        requestBody.put("TokenType", "JWT")
        requestBody.put("RelyingParty", "http://auth.xboxlive.com")

        val client = OkHttpClient()
        val request = Request.Builder()
            .post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
            .url("https://user.auth.xboxlive.com/user/authenticate")
            .build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = ObjectMapper().readTree(body.bytes())

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

        val client = OkHttpClient()
        val request = Request.Builder()
            .post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
            .url("https://xsts.auth.xboxlive.com/xsts/authorize")
            .build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = ObjectMapper().readTree(body.bytes())

            return root["Token"].asText() to root["DisplayClaims"]["xui"][0]["uhs"].asText()
        }
    }

    /**
     * @return Minecraft access token if successful, else null
     */
    private fun loginMinecraftServices(token: String, uhs: String): String? {
        val requestBody = ObjectNode(JsonNodeFactory.instance)
        requestBody.put("identityToken", "XBL3.0 x=$uhs;$token")

        val client = OkHttpClient()
        val request = Request.Builder()
            .post(requestBody.toString().toRequestBody(contentType = "application/json".toMediaType()))
            .url("https://api.minecraftservices.com/authentication/login_with_xbox")
            .build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = ObjectMapper().readTree(body.bytes())

            return root["access_token"].asText()
        }
    }

    /**
     * @return Pair containing player uuid and username if successful, else null
     */
    private fun getUserdetails(token: String): Pair<String, String>? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .get()
            .url("https://api.minecraftservices.com/minecraft/profile")
            .header("Authorization", "Bearer $token")
            .build()

        client.newCall(request).execute().use {
            if (it.code != 200) {
                return null
            }

            val body = it.body!!
            val root = ObjectMapper().readTree(body.bytes())

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

            engine.loadWorker.stateProperty().addListener { observable, oldValue, newValue ->
                if (newValue == Worker.State.SUCCEEDED && engine.location.startsWith("http://localhost")) {
                    Platform.exit()
                }
            }

            engine.load("https://login.live.com/oauth20_authorize.srf?response_type=code&client_id=$appID&redirect_uri=http://localhost:$serverPort/auth&scope=XboxLive.signin+offline_access")
            println(engine.location)
            Thread {
                token = MSAuthLocalServer.waitForToken(serverPort)
            }.start()
            val box = VBox(view)
            val scene = Scene(box, 680.0, 480.0)

            stage.scene = scene
            stage.show()
        }
    }
}