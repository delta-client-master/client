package com.deltaclient.common.feature.impl.keystrokes

import com.deltaclient.common.DeltaClient.drawableHelper
import com.deltaclient.common.DeltaClient.mc
import com.deltaclient.common.bridge.math.IMatrixStackBridge
import com.deltaclient.common.bridge.option.IKeyBindingBridge
import com.deltaclient.common.event.impl.RenderOverlayEvent
import com.deltaclient.common.feature.AbstractDraggableHUDFeature
import com.deltaclient.common.feature.FeatureCategory
import java.awt.Color

private const val KEY_SIZE = 26F
private const val SPACING = 2F

class KeystrokesFeature : AbstractDraggableHUDFeature("Keystrokes", FeatureCategory.HUD) {
    init {
        toggle()
        x = 5F
        y = 25F
    }

    override fun calculateBounds(matrices: IMatrixStackBridge): Pair<Float, Float> {
        return Pair(0f, 0f)
    }

    override fun drawFeature(event: RenderOverlayEvent) {
        //   W
        // A S D
        // SPACE
        // L   R - clicks

        var y = this.y
        var x = this.x

        // forward key
        drawableHelper.fill(
            event.matrices,
            x + SPACING + KEY_SIZE,
            y,
            x + SPACING + (KEY_SIZE * 2),
            y + KEY_SIZE,
            getColor(mc.options.keyForward, true)
        )

        mc.textRenderer.draw(
            event.matrices,
            mc.options.keyForward.name,
            x + SPACING + KEY_SIZE + KEY_SIZE / 2 - mc.textRenderer.getWidth(mc.options.keyForward.name) / 2,
            y + mc.textRenderer.getHeight(),
            getColor(mc.options.keyForward, false)
        )

        y += KEY_SIZE + SPACING

        arrayOf(mc.options.keyLeft, mc.options.keyBack, mc.options.keyRight).forEach {
            drawableHelper.fill(event.matrices, x, y, x + KEY_SIZE, y + KEY_SIZE, getColor(it, true))

            mc.textRenderer.draw(
                event.matrices,
                it.name,
                x + KEY_SIZE / 2 - mc.textRenderer.getWidth(it.name) / 2,
                y + mc.textRenderer.getHeight(),
                getColor(it, false)
            )

            x += KEY_SIZE + SPACING
        }
    }

    private fun getColor(key: IKeyBindingBridge, bg: Boolean): Int {
        return if (bg) {
            if (key.pressed) Color.WHITE.rgb else Color.BLACK.rgb
        } else {
            if (key.pressed) Color.BLACK.rgb else Color.WHITE.rgb
        }
    }
}