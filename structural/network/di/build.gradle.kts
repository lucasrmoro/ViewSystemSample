import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.NETWORK_API))
    implementation(project(Modules.Structural.NETWORK_IMPL))
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Structural.SESSION_API))
    implementation(project(Modules.Structural.LOCAL_STORAGE_API))
    implementation(project(Modules.Feature.Login.DOMAIN_API))
    implementation(libs.bundles.retrofit)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(project(Modules.Structural.DI_API))
}
