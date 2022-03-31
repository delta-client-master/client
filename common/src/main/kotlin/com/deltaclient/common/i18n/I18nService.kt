package com.deltaclient.common.i18n

import com.deltaclient.common.DeltaClient.mc
import java.io.ByteArrayOutputStream
import java.util.zip.ZipInputStream

object I18nService {
    private val languages = mutableMapOf<String, Map<String, String>>()
    private const val defaultLang = "en_US"

    private val currentLang: String
        get() {
            return mc.languageManager.currentLanguageCode
        }

    fun load() {
        val source = I18nService::class.java.protectionDomain.codeSource
        val url = source.location
        ZipInputStream(url.openStream()).use { zip ->
            while (true) {
                val entry = zip.nextEntry ?: break // EOF
                if (entry.isDirectory) {
                    continue
                }

                val fullName = entry.name
                if (fullName.startsWith("assets/lang/")) {
                    val name = fullName.substringAfter("assets/lang/").substringBefore(".lang")
                    val lines = ByteArrayOutputStream().use { os ->
                        val buffer = ByteArray(2048)
                        var len: Int
                        while (zip.read(buffer).also { len = it } > 0) {
                            os.write(buffer, 0, len)
                        }

                        os.toByteArray().decodeToString()
                    }.split('\n')

                    val langMap = mutableMapOf<String, String>()
                    lines.forEach { line ->
                        val key = line.substringBefore("=")
                        val value = line.substringAfter("=")
                        langMap[key] = value
                    }

                    languages[name] = langMap
                }

                zip.closeEntry()
            }
        }
    }

    fun translate(key: String): String {
        val langMap = languages[currentLang] ?: languages[defaultLang]!!
        return langMap[key] ?: key
    }
}