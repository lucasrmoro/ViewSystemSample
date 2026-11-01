import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.PERMISSIONS_API))
    implementation(project(Modules.Structural.PERMISSIONS_IMPL))
    implementation(libs.androidx.activity.ktx)
    implementation(project(Modules.Structural.DI_API))
}
