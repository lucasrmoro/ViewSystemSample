import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "br.com.viewsystem_sample.core_network"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.ENTITIES))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(libs.bundles.retrofit)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.coroutines)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}