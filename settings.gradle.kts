pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
            metadataSources {
                gradleMetadata()
                mavenPom()
                artifact()
            }
        }
        maven { url = uri("https://hub.megan.ir/maven") }
//        maven { url = uri("https://gradle.jamko.ir") }
//        maven { url = uri("https://mirror-maven.runflare.com/maven2") }
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
//        mavenCentral()
//        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://maven.myket.ir")
            metadataSources {
                gradleMetadata()
                mavenPom()
                artifact()
            }
        }
        maven { url = uri("https://hub.megan.ir/maven") }
//        maven { url = uri("https://gradle.jamko.ir") }
//        maven { url = uri("https://mirror-maven.runflare.com/maven2") }
//        google()
//        mavenCentral()
//        maven { url = uri("https://jitpack.io") }

    }
}

rootProject.name = "My Shatel Mobile Widget"
include(":app")
