import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(project(Modules.Structural.SESSION_API))
    api(project(Modules.Structural.NETWORK_API))
    implementation(libs.bundles.android)
    api(project(Modules.Structural.DI_API))
}
