import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(project(Modules.Structural.Biometric.API))
    implementation(project(Modules.Structural.Permissions.API))
    implementation(project(Modules.Structural.Logger.API))
    implementation(project(Modules.Structural.Core.MAIN))
    implementation(libs.bundles.android)
    api(project(Modules.Structural.DI.API))
}