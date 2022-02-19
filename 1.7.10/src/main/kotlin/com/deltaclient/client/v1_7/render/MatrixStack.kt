package com.deltaclient.client.v1_7.render

import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.render.IMatrixStackEntryBridge
import com.mojang.blaze3d.platform.GLX
import org.lwjgl.opengl.GL11

object MatrixStack : IMatrixStackBridge {
    override fun push() {
        // blend is for text rendering
        GL11.glPushMatrix()
        GL11.glEnable(3042)
        GLX.glBlendFuncSeparate(770, 771, 1, 0)
    }

    override fun pop() {
        GL11.glDisable(3042)
        GL11.glPopMatrix()
    }

    override fun scale(x: Float, y: Float, z: Float) {
        GL11.glScalef(x, y, z)
    }

    override fun peek(): IMatrixStackEntryBridge {
        TODO("Not yet implemented")
    }
}