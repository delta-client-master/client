package com.deltaclient.common.msa

import io.javalin.Javalin

object MSAuthLocalServer {
    private var token = ""

    fun waitForToken(port: Int): String {
        val app = Javalin.create().start(port)
        app.get("/auth") {
            token = it.req.queryString.split("=")[1]
            it.result("Done!")
        }

        @Suppress("ControlFlowWithEmptyBody") while (token.isEmpty()) {
            Thread.sleep(1)
        }

        return token
    }
}