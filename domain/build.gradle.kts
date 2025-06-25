import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.DATA))
    implementation(project(Modules.ENTITIES))
    implementation(project(Modules.CORE_UI))
    implementation(libs.coroutines)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}