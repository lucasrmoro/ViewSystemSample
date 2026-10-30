import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.LOGGER_API))
    add("debugImplementation", libs.timber)
    implementation(libs.koin.core)
}
