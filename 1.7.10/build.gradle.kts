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
    minecraft("com.mojang:minecraft:1.7.10")
    mappings("net.fabricmc:yarn:1.7.10+build.202201302314:v2")
    modImplementation("net.fabricmc:fabric-loader:0.12.12")

    implementation(project(":common"))
}

loom {
    val dir = project.projectDir.toString() + File.separatorChar + "assets" + File.separatorChar + "client.jar"
    clientJarPath = dir

    intermediaryUrl = object : java.util.function.Function<String, Any> {
        override fun apply(p1: String): Any {
            return "https://maven.legacyfabric.net/net/fabricmc/intermediary/$p1/intermediary-$p1-v2.jar"
        }
    }

    runs {
        getByName("client") {
            runDir = "1.7.10" + File.separatorChar + "run"
            programArgs.add("--userProperties")
            programArgs.add("{}")
        }
    }
}