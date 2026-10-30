import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(project(Modules.Feature.Login.DOMAIN_API))
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Structural.NETWORK_API))
    implementation(project(Modules.Structural.LOCAL_STORAGE))
    implementation(libs.bundles.retrofit)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.coroutines)
    implementation(libs.bundles.koin)
    ksp(libs.koin.compiler)
}