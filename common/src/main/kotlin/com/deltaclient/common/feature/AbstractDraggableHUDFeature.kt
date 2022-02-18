package com.deltaclient.common.feature

import com.deltaclient.common.event.impl.RenderOverlayEvent

// TODO: Implement dragging of features
//  Probably should store a width/height for dragging?

abstract class AbstractDraggableHUDFeature : IFeature {
    var x: Float = 0F
    var y: Float = 0F

    var scale: Float = 1F

    abstract fun draw(event: RenderOverlayEvent)

    // Overriding these as text features shouldn't need them
    override fun onEnable() = Unit
    override fun onDisable() = Unit
}