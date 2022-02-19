package com.deltaclient.client.v1_8.render

import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.render.IMatrixStackEntryBridge
import com.mojang.blaze3d.platform.GlStateManager
import org.lwjgl.opengl.GL11

object MatrixStack : IMatrixStackBridge {
    override fun push() {
        // blend is for text rendering
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.blendFuncSeparate(770, 771, 1, 0)
    }

    override fun pop() {
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    override fun scale(x: Float, y: Float, z: Float) {
        GL11.glScalef(x, y, z)
    }

    override fun peek(): IMatrixStackEntryBridge {
        TODO("Not yet implemented")
    }
}