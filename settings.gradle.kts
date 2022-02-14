pluginManagement {
    repositories {
        gradlePluginPortal()

        val DELTA_NX_USER: String by settings
        val DELTA_NX_PASS: String by settings

        maven("https://nexus.deltaclient.com/repository/internal/") {
            authentication {
                credentials {
                    username = DELTA_NX_USER
                    password = DELTA_NX_PASS
                }
            }
        }

        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")
    }
}

rootProject.name = "client"
include("1.8.9")
include("1.7.10")
include("common")
include("1.18.1")
