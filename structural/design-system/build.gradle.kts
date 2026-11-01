import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(project(Modules.Structural.BIOMETRIC_API))
    implementation(project(Modules.Structural.PERMISSIONS_API))
    implementation(project(Modules.Structural.LOGGER_API))
    implementation(project(Modules.Structural.CORE))
    implementation(libs.bundles.android)
    api(project(Modules.Structural.DI_API))
}