package com.deltaclient.client.v1_7.render

import com.deltaclient.client.v1_7.texture.CustomSprite
import com.deltaclient.common.bridge.render.IMatrixStackBridge
import com.deltaclient.common.bridge.texture.ISpriteBridge
import com.deltaclient.common.bridge.util.IDrawableHelperBridge
import com.deltaclient.common.ext.cast
import com.mojang.blaze3d.platform.GLX
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawableHelper
import net.minecraft.client.render.Tessellator
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

        val var10 = (color shr 24 and 255).toFloat() / 255.0f
        val var6 = (color shr 16 and 255).toFloat() / 255.0f
        val var7 = (color shr 8 and 255).toFloat() / 255.0f
        val var8 = (color and 255).toFloat() / 255.0f
        val var9 = Tessellator.INSTANCE
        GL11.glEnable(3042)
        GL11.glDisable(3553)
        GLX.glBlendFuncSeparate(770, 771, 1, 0)
        GL11.glColor4f(var6, var7, var8, var10)
        var9.method_1405()
        var9.method_1398(x1.toDouble(), y2.toDouble(), 0.0)
        var9.method_1398(x2.toDouble(), y2.toDouble(), 0.0)
        var9.method_1398(x2.toDouble(), y1.toDouble(), 0.0)
        var9.method_1398(x1.toDouble(), y1.toDouble(), 0.0)
        var9.method_1396()
        GL11.glEnable(3553)
        GL11.glDisable(3042)
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
        matrices: IMatrixStackBridge, x: Int, y: Int, width: Int, height: Int, sprite: ISpriteBridge
    ) {
        val id = sprite.atlas.cast<Identifier>()
        MinecraftClient.getInstance().textureManager.bindTexture(id)

        val customSprite = sprite.cast<CustomSprite>()
        GL11.glColor4f(1F, 1F, 1F, 1F)
        drawTexture(x, y, customSprite.u, customSprite.v, customSprite.width, customSprite.height)
    }
}