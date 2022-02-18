plugins {
    kotlin("jvm") version "1.6.10" apply false
}

subprojects {
    repositories {
        maven("https://repo.lillian.dev/releases")
    }
}