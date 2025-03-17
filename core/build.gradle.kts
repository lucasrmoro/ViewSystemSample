import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.viewsystem_sample.core"
}

dependencies {
    implementation(project(Modules.ENTITIES))
    implementation(libs.bundles.android)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
}