plugins {
    java
    kotlin("jvm")
}

group = "com.deltaclient"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("com.mojang:authlib:3.2.38")

    implementation(kotlin("reflect"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}