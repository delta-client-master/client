package com.deltaclient.common.config

import com.deltaclient.common.Delta
import com.deltaclient.common.feature.property.PropertyService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import kotlin.io.path.*

/**
 * In the future we will need to support having multiple configurations, but this is okay for now
 */
object FeatureConfig {
    private val path = Path(System.getProperty("user.home"), ".deltaclient")
    private val featureConfigPath = path.resolve("features.json")

    private val objectMapper = ObjectMapper()

    init {
        if (!path.exists()) {
            path.createDirectories()
        }
    }

    fun load() {
        if (!featureConfigPath.exists()) {
            return
        }

        val jsonBytes = featureConfigPath.readBytes()
        val rootNode = objectMapper.readTree(jsonBytes) ?: return
        for (featureNode in rootNode) {
            val name = featureNode["name"].asText()

            // TODO: Just store features in a hashmap :shrug:
            val feature = Delta.featureService.features.find { it.name == name } ?: return

            feature.enabled = featureNode["enabled"].booleanValue()

            val propertiesNode = featureNode["properties"]
            PropertyService.getProperties(feature)?.forEach { it.deserialize(propertiesNode) }
        }
    }

    fun save() {
        val rootNode = objectMapper.createArrayNode()
        Delta.featureService.features.forEach { feature ->
            val featureNode = objectMapper.createObjectNode()
            featureNode.put("name", feature.name)
            featureNode.put("enabled", feature.enabled)

            val propertiesNode = objectMapper.createObjectNode()
            PropertyService.getProperties(feature)?.forEach { it.serialize(propertiesNode) }

            featureNode.set<ObjectNode>("properties", propertiesNode)
            rootNode.add(featureNode)
        }

        featureConfigPath.writeBytes(rootNode.toString().encodeToByteArray())
    }
}