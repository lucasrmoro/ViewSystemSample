import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.Structural.ENTITIES))
    implementation(libs.bundles.android)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
}