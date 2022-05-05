plugins {
    kotlin("jvm") version "1.6.10" apply false
}

subprojects {
    repositories {
        maven("https://jitpack.io")
        maven("https://nexus.deltaclient.com/repository/internal/") {
            val deltaNxUser: String by project
            val deltaNxPass: String by project
            authentication {
                credentials {
                    username = deltaNxUser
                    password = deltaNxPass
                }
            }
        }
    }
}