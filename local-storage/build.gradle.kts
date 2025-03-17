import br.com.viewsystem_sample.modules.Modules
import br.com.viewsystem_sample.ext.ksp

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.viewsystem_sample.local_storage"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.ENTITIES))
    implementation(libs.androidx.security)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}