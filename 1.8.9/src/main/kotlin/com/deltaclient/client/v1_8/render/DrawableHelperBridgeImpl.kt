package com.deltaclient.client.v1_8.render

import com.deltaclient.client.v1_8.texture.CustomSprite
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.bridge.texture.ISpriteBridge
import com.deltaclient.common.bridge.util.IDrawableHelperBridge
import com.deltaclient.common.ext.cast
import com.mojang.blaze3d.platform.GlStateManager
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormats
import net.minecraft.util.Identifier
import org.lwjgl.opengl.GL11

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

    override fun drawTexture(
        matrices: IMatrixStackBridge,
        x: Int,
        y: Int,
        z: Int,
        u: Float,
        v: Float,
        width: Int,
        height: Int,
        textureWidth: Int,
        textureHeight: Int
    ) {
        drawTexture(x, y, u, v, width, height, textureWidth.toFloat(), textureHeight.toFloat())
    }

    override fun drawSprite(
        matrices: IMatrixStackBridge,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        sprite: ISpriteBridge
    ) {
        val id = sprite.atlas.cast<Identifier>()
        MinecraftClient.getInstance().textureManager.bindTexture(id)

        val customSprite = sprite.cast<CustomSprite>()
        GL11.glColor4f(1F, 1F, 1F, 1F)
        drawTexture(x, y, customSprite.u, customSprite.v, customSprite.width, customSprite.height)
    }
}