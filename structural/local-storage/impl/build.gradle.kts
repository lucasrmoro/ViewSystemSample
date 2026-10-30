import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Feature.FeatureA.DATA))
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(project(Modules.Structural.LOCAL_STORAGE_API))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.tink)
    implementation(libs.tink.android)
    implementation(libs.bundles.koin)
}
