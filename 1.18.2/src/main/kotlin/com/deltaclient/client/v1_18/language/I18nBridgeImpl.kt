package com.deltaclient.client.v1_18.language

import com.deltaclient.common.bridge.language.II18nBridge
import net.minecraft.client.resource.language.I18n

object I18nBridgeImpl : II18nBridge {
    override fun translate(key: String, vararg args: Any): String = I18n.translate(key, args)
}