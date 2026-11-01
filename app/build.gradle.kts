import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.app)
}

dependencies {
    implementation(project(Modules.Structural.DI_IMPL))
    implementation(project(Modules.Structural.ANALYTICS_IMPL))
    implementation(project(Modules.Structural.REMOTE_CONFIG_IMPL))
    implementation(project(Modules.Feature.FeatureA.NAVIGATION_API))
    implementation(project(Modules.Feature.Login.DI))
    implementation(project(Modules.Feature.FeatureA.DI))
}