package com.deltaclient.common.event.impl

import com.deltaclient.common.bridge.math.IMatrixStackBridge

class RenderOverlayEvent(val matrices: IMatrixStackBridge, val tickDelta: Float)