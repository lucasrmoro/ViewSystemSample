import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {

    implementation(project(Modules.Structural.REMOTE_CONFIG_API))
    implementation(project(Modules.Structural.REMOTE_CONFIG_IMPL))
    implementation(project(Modules.Structural.DI_API))
}
