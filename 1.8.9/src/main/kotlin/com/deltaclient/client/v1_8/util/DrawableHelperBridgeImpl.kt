package com.deltaclient.client.v1_8.util

import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.util.IDrawableHelperBridge
import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormats

object DrawableHelperBridgeImpl : IDrawableHelperBridge, DrawableHelper() {
    @Suppress("NAME_SHADOWING")
    override fun fill(matrices: IMatrixStackBridge, x1: Float, y1: Float, x2: Float, y2: Float, color: Int) {
        var x1 = x1
        var x2 = x2
        var y1 = y1
        var y2 = y2

        var swapTemp: Float
        if (x1 < x2) {
            swapTemp = x1
            x1 = x2
            x2 = swapTemp
        }

        if (y1 < y2) {
            swapTemp = y1
            y1 = y2
            y2 = swapTemp
        }

        val f = (color shr 24 and 255).toFloat() / 255.0f
        val g = (color shr 16 and 255).toFloat() / 255.0f
        val h = (color shr 8 and 255).toFloat() / 255.0f
        val k = (color and 255).toFloat() / 255.0f

        val tessellator = Tessellator.getInstance()
        val bufferBuilder = tessellator.buffer
        GlStateManager.enableBlend()
        GlStateManager.disableTexture()
        GlStateManager.blendFuncSeparate(770, 771, 1, 0)
        GlStateManager.color4f(g, h, k, f)
        bufferBuilder.begin(7, VertexFormats.POSITION)
        bufferBuilder.vertex(x1.toDouble(), y2.toDouble(), 0.0).next()
        bufferBuilder.vertex(x2.toDouble(), y2.toDouble(), 0.0).next()
        bufferBuilder.vertex(x2.toDouble(), y1.toDouble(), 0.0).next()
        bufferBuilder.vertex(x1.toDouble(), y1.toDouble(), 0.0).next()
        tessellator.draw()
        GlStateManager.enableTexture()
        GlStateManager.disableBlend()
    }
}