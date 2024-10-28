rootProject.name = "ScanbotBarcodeScanner"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
                maven { url = java.net.URI("https://nexus.scanbot.io/nexus/content/repositories/releases/") }
                maven { url = java.net.URI("https://nexus.scanbot.io/nexus/content/repositories/snapshots/") }
            }
        }
        mavenCentral()
    }
}

include(":composeApp")