plugins {
    java
    kotlin("jvm")
    id("org.openjfx.javafxplugin") version "0.0.10"
}

group = "com.deltaclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
    maven("https://maven.fabricmc.net")
}

javafx {
    version = "17.0.1"
    modules("javafx.controls", "javafx.web")
}

dependencies {
    compileOnly("com.mojang:authlib:3.2.38")
    compileOnly("net.fabricmc:sponge-mixin:0.11.1+mixin.0.8.5")

    implementation("dev.lillian.bonk:core:1.0")
    implementation("io.javalin:javalin:4.3.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.2")

    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
}