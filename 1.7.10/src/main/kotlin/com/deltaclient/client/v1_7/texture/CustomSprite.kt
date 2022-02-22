package com.deltaclient.client.v1_7.texture

import com.deltaclient.common.bridge.texture.ISpriteBridge
import com.deltaclient.common.bridge.util.IIdentifierBridge

class CustomSprite(override val atlas: IIdentifierBridge, val u: Int, val v: Int, val width: Int, val height: Int) :
    ISpriteBridge