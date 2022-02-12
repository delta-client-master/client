plugins {
    java
    id("fabric-loom") version "0.7-SNAPSHOT"
}

group = "com.deltaclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.legacyfabric.net")
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("net.fabricmc:yarn:1.8.9+build.202201302314:v2")
    modImplementation("net.fabricmc:fabric-loader:0.12.12")
}

minecraft {
    intermediaryUrl = object : java.util.function.Function<String, Any> {
        override fun apply(p1: String): Any {
            return "https://maven.legacyfabric.net/net/fabricmc/intermediary/" + p1 + "/intermediary-" + p1 + "-v2.jar"
        }
    }
}

loom {
    val dir = project.rootDir.toString() + File.separatorChar + "assets" + File.separatorChar + "client.jar"
    clientJarPath = dir
}