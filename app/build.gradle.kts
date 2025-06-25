import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.app)
}

quadrantConfig.generateByDefault = false

dependencies {
    implementation(project(Modules.CORE_NETWORK))
    implementation(project(Modules.LOCAL_STORAGE))
    implementation(project(Modules.DATA))
    implementation(project(Modules.DOMAIN))
    implementation(libs.timber)
    implementation(libs.bundles.koin)
}