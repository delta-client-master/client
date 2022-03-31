package com.deltaclient.common.feature.property.impl

import com.deltaclient.common.feature.property.AbstractProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import java.awt.Color

class ColorProperty(name: String, default: Color) : AbstractProperty<Color>(name, default) {
    override fun serialize(propertiesNode: ObjectNode) {
        propertiesNode.put(name, value.rgb)
    }

    override fun deserialize(propertiesNode: JsonNode) {
        val thisProp = propertiesNode[name] ?: return
        val rgba = thisProp.intValue()
        value = Color(rgba)
    }
}