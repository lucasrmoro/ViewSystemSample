import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    namespace = "br.com.viewsystem_sample.core_data"
}

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.ENTITIES))
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.koin)
    compileOnly(libs.koin.compiler)
}