package com.deltaclient.client.v1_18.util

import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.texture.ISpriteBridge
import com.deltaclient.common.bridge.util.IDrawableHelperBridge
import com.deltaclient.common.ext.cast
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.BufferRenderer
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat.DrawMode
import net.minecraft.client.render.VertexFormats
import net.minecraft.client.texture.Sprite
import net.minecraft.util.Identifier
import net.minecraft.util.math.Matrix4f

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

        val i = (color shr 24 and 255).toFloat() / 255.0f
        val f = (color shr 16 and 255).toFloat() / 255.0f
        val g = (color shr 8 and 255).toFloat() / 255.0f
        val h = (color and 255).toFloat() / 255.0f

        val bufferBuilder = Tessellator.getInstance().buffer
        RenderSystem.enableBlend()
        RenderSystem.disableTexture()
        RenderSystem.defaultBlendFunc()
        RenderSystem.setShader(GameRenderer::getPositionColorShader)

        val posMatrix = matrices.peek().positionMatrix.cast<Matrix4f>()

        bufferBuilder.begin(DrawMode.QUADS, VertexFormats.POSITION_COLOR)
        bufferBuilder.vertex(posMatrix, x1, y2, 0.0f).color(f, g, h, i).next()
        bufferBuilder.vertex(posMatrix, x2, y2, 0.0f).color(f, g, h, i).next()
        bufferBuilder.vertex(posMatrix, x2, y1, 0.0f).color(f, g, h, i).next()
        bufferBuilder.vertex(posMatrix, x1, y1, 0.0f).color(f, g, h, i).next()
        bufferBuilder.end()

        BufferRenderer.draw(bufferBuilder)
        RenderSystem.enableTexture()
        RenderSystem.disableBlend()
    }

    override fun drawSprite(
        matrices: IMatrixStackBridge, x: Int, y: Int, width: Int, height: Int, sprite: ISpriteBridge
    ) {
        RenderSystem.setShaderTexture(0, sprite.atlas.cast<Identifier>())
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f)
        drawSprite(matrices.cast(), x, y, 0, width, height, sprite.cast())
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
        drawTexture(matrices.cast(), x, y, z, u, v, width, height, textureWidth, textureHeight)
    }
}