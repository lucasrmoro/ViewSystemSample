import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

android {
    namespace = "br.com.viewsystem_sample.entities"
}

dependencies {
    implementation(libs.gson)
    implementation(libs.bundles.room)
}