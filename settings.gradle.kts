pluginManagement {
    repositories {
        gradlePluginPortal()

        val deltaNxUser: String by settings
        val deltaNxPass: String by settings

        maven("https://nexus.deltaclient.com/repository/internal/") {
            authentication {
                credentials {
                    username = deltaNxUser
                    password = deltaNxPass
                }
            }
        }

        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")
    }
}

rootProject.name = "client"
include("common")
//include("1.8.9")
include("1.7.10")
include("1.18.1")
