import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.PUSH_NOTIFICATIONS_API))
    implementation(project(Modules.Structural.NOTIFICATIONS_API))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
    implementation(libs.coroutinesPlayServices)
    implementation(libs.androidx.core.ktx)
}
