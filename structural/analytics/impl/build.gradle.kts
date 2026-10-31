import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.ANALYTICS_API))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}
