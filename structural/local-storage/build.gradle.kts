import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Structural.ENTITIES))
    implementation(libs.androidx.security)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}