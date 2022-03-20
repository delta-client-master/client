package com.deltaclient.common.socket

import com.deltaclient.backend.common.packet.PacketProcessor
import com.deltaclient.backend.common.packet.user.C2SVerificationResponsePacket
import com.deltaclient.backend.common.packet.user.S2CVerificationRequestPacket
import com.deltaclient.backend.common.util.CryptoUtil
import com.deltaclient.common.Delta.mc
import com.mojang.authlib.exceptions.AuthenticationException
import okhttp3.*
import okio.ByteString
import okio.ByteString.Companion.toByteString
import java.util.concurrent.ForkJoinPool

class WebSocketClient(private val url: String) {

    private val httpClient = OkHttpClient()

    fun connect() {
        ForkJoinPool.commonPool().execute {
            httpClient.newWebSocket(
                Request.Builder().url(url).headers(
                    Headers.headersOf("Username", mc.session.username)
                ).build(),
                object : WebSocketListener() {
                    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                        t.printStackTrace()
                    }

                    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                        val buffer = bytes.asByteBuffer()
                        val packet = PacketProcessor.deserialize(buffer)

                        when (packet) {
                            is S2CVerificationRequestPacket -> {
                                val public = packet.publicKey
                                val secret = CryptoUtil.createNewSharedKey()
                                val serverId = CryptoUtil.getRadixServerId(
                                    "deltawebsocket",
                                    public,
                                    secret
                                )

                                try {
                                    mc.sessionService.joinServer(mc.session.profile, mc.session.accessToken, serverId)
                                } catch (e: AuthenticationException) {
                                    webSocket.close(69, "Authentication error. Aborting websocket connection")
                                }

                                val response = C2SVerificationResponsePacket(secret, public, packet.verifyToken)
                                val serialized = PacketProcessor.serialize(response)
                                webSocket.send(serialized.array().toByteString())
                            }
                        }
                    }

                    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                        println("[Delta] WebSocket connection closed with code $code and reason $reason")
                    }
                })
        }
    }
}