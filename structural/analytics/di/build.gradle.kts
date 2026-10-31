import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.ANALYTICS_API))
    implementation(project(Modules.Structural.ANALYTICS_IMPL))
    implementation(libs.koin.core)
}
