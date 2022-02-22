package com.deltaclient.common.bridge.language

@Suppress("INAPPLICABLE_JVM_NAME")
interface II18nBridge {
    @JvmName("bridge\$translate")
    fun translate(key: String, vararg args: Any): String
}