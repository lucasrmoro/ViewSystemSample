import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.feature)
}

dependencies {
    implementation(project(Modules.DOMAIN))
}