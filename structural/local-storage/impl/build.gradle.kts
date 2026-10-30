import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.Feature.FeatureA.DATA))
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(project(Modules.Structural.LOCAL_STORAGE_API))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.security)
    implementation(libs.bundles.koin)
}
