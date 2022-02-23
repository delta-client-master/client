plugins {
    java
    kotlin("jvm")
}

group = "com.deltaclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
    maven("https://maven.fabricmc.net")
}

dependencies {
    compileOnly("com.mojang:authlib:3.2.38")
    compileOnly("net.fabricmc:sponge-mixin:0.11.1+mixin.0.8.5")

    implementation("dev.lillian.bonk:core:1.0")

    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}