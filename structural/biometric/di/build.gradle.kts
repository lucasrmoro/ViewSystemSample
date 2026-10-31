import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.BIOMETRIC_API))
    implementation(project(Modules.Structural.BIOMETRIC_IMPL))
    implementation(project(Modules.Structural.CORE))
    implementation(libs.androidx.biometric)
    implementation(libs.koin.core)
}
