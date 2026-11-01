import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(project(Modules.Structural.SESSION_API))
    implementation(project(Modules.Feature.Login.DOMAIN_API))
    implementation(project(Modules.Structural.CORE))
    implementation(project(Modules.Structural.NETWORK_API))
    implementation(project(Modules.Structural.LOCAL_STORAGE_API))
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.okhttp.client)
    debugImplementation(libs.okhttp.loggingInterceptor)
    implementation(libs.coroutines)
}