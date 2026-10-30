import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.CORE))
    implementation(libs.bundles.android)
    implementation(libs.bundles.koin)
}