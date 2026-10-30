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
// Structural
include(
    ":structural",
    ":structural:core",
    ":structural:session:api",
    ":structural:session:impl",
    ":structural:permissions:api",
    ":structural:permissions:impl",
    ":structural:logger:api",
    ":structural:logger:impl",
    ":structural:network",
    ":structural:network:api",
    ":structural:network:impl",
    ":structural:designsystem",
    ":structural:local-storage",
    ":structural:local-storage:api",
    ":structural:local-storage:impl"
)

// Features
include(
    ":features",
    ":features:featureA",
    ":features:featureA:di:api",
    ":features:featureA:di:impl",
    ":features:featureA:data",
    ":features:featureA:domain:api",
    ":features:featureA:domain:impl",
    ":features:featureA:presentation",
    ":features:featureA:navigation:api",
    ":features:login:domain:api",
    ":features:login:data",
    ":features:login:di:api",
    ":features:login:di:impl"
)
