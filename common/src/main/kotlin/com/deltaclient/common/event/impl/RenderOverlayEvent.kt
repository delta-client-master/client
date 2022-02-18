package com.deltaclient.common.event.impl

import com.deltaclient.common.bridge.render.IMatrixStackBridge

class RenderOverlayEvent(val matrices: IMatrixStackBridge, val tickDelta: Float)