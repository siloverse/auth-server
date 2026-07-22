pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/siloverse/siloverse-build")
            credentials {
                username = providers.gradleProperty("gpr.user").orElse(System.getenv("GITHUB_ACTOR")).orNull
                password = providers.gradleProperty("gpr.key").orElse(System.getenv("GITHUB_TOKEN")).orNull
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            val markerArtifactId = when (requested.id.id) {
                "io.github.siloverse.kotlin-library" -> "kotlin-library-plugin"
                "io.github.siloverse.kotlin-application" -> "kotlin-application-plugin"
                "io.github.siloverse.spring-boot-application" -> "spring-boot-application-plugin"
                else -> null
            }

            if (markerArtifactId != null) {
                useModule("io.github.siloverse.gradle:$markerArtifactId:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/siloverse/siloverse-build")
            credentials {
                username = providers.gradleProperty("gpr.user").orElse(System.getenv("GITHUB_ACTOR")).orNull
                password = providers.gradleProperty("gpr.key").orElse(System.getenv("GITHUB_TOKEN")).orNull
            }
        }
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from("io.github.siloverse.gradle:version-catalog:1.0.2")
        }
    }
}

rootProject.name = "auth-server"

include("silo")
include("web")
include("messages")
include("ui")

//includeBuild("build-logic")