package com.deltaclient.common.feature.property.impl

import com.deltaclient.common.feature.property.AbstractProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

class IntProperty(name: String, default: Int) : AbstractProperty<Int>(name, default) {
    override fun serialize(propertiesNode: ObjectNode) {
        propertiesNode.put(name, value)
    }

    override fun deserialize(propertiesNode: JsonNode) {
        val thisProp = propertiesNode[name] ?: return
        value = thisProp.intValue()
    }
}