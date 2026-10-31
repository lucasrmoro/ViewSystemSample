import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.LOCAL_STORAGE_API))
    implementation(project(Modules.Structural.LOCAL_STORAGE_IMPL))
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Feature.FeatureA.DATA))
    implementation(libs.koin.core)
}
