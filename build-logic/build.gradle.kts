plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.quadrant)
    compileOnly(libs.android.gradle.tools)
    compileOnly(libs.kotlin.gradle.plugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApp") {
            id = libs.plugins.sample.android.app.get().pluginId
            implementationClass = "br.com.viewsystem_sample.plugins.AndroidAppPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.sample.android.library.get().pluginId
            implementationClass = "br.com.viewsystem_sample.plugins.AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = libs.plugins.sample.android.feature.get().pluginId
            implementationClass = "br.com.viewsystem_sample.plugins.AndroidFeaturePlugin"
        }
    }
}