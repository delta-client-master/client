plugins {
    java
    id("fabric-loom") version "0.10-SNAPSHOT"
}

group = "com.deltaclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    minecraft("com.mojang:minecraft:1.18.1")
    mappings("net.fabricmc:yarn:1.18.1+build.22:v2")
    modImplementation("net.fabricmc:fabric-loader:0.13.1")

    implementation(project(":common"))
}

loom {
    runs {
        getByName("client") {
            runDir = "1.18.1" + File.separatorChar + "run"
        }
    }
}