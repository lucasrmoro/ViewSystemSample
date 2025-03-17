import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.feature)
}

android {
    namespace = "br.com.viewsystem_sample.featurea"
}

dependencies {
    implementation(project(Modules.DOMAIN))
}