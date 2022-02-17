package com.deltaclient.common.bridge.lang

@Suppress("INAPPLICABLE_JVM_NAME")
interface ILanguageManagerBridge {
    @get:JvmName("bridge\$getCurrentLanguageCode")
    val currentLanguageCode: String
}