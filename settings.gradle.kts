pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
    }
}

rootProject.name = "ViewSystem - Sample"

include(":app")
include(":core")
include(":core-ui")
include(":core-network")
include(":entities")
include(":local-storage")

// Features
include(
    ":features",
    ":features:featureA",
    ":features:featureA:di",
    ":features:featureA:data:api",
    ":features:featureA:data:impl",
    ":features:featureA:domain:api",
    ":features:featureA:domain:impl",
    ":features:featureA:presentation"
)
include(":features:featureA:di:api")
include(":features:featureA:di:impl")
