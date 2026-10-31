import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.LOGGER_API))
    implementation(libs.gson)

    implementation(project(Modules.Structural.REMOTE_CONFIG_API))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
    implementation(libs.coroutinesPlayServices)
}
