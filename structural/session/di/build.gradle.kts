import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(project(Modules.Structural.SESSION_API))
    implementation(project(Modules.Structural.SESSION_IMPL))
    implementation(project(Modules.Structural.DI_API))
}
