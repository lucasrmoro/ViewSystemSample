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
    ":structural:core:di",
    ":structural:biometric:api",
    ":structural:biometric:impl",
    ":structural:biometric:di",
    ":structural:session:api",
    ":structural:session:impl",
    ":structural:session:di",
    ":structural:permissions:api",
    ":structural:permissions:impl",
    ":structural:permissions:di",
    ":structural:logger:api",
    ":structural:logger:impl",
    ":structural:logger:di",
    ":structural:network",
    ":structural:network:api",
    ":structural:network:impl",
    ":structural:network:di",
    ":structural:designsystem",
    ":structural:designsystem:di",
    ":structural:local-storage",
    ":structural:local-storage:api",
    ":structural:local-storage:impl",
    ":structural:local-storage:di"
)

// Features
include(
    ":features",
    ":features:featureA",
    ":features:featureA:di",
    ":features:featureA:data",
    ":features:featureA:domain:api",
    ":features:featureA:domain:impl",
    ":features:featureA:presentation",
    ":features:featureA:navigation:api",
    ":features:login:domain:api",
    ":features:login:data",
    ":features:login:di"
)
