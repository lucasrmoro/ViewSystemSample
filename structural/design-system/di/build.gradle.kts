import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.DESIGN_SYSTEM))
    implementation(project(Modules.Structural.BIOMETRIC_API))
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.core)
}
