import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.app)
}

dependencies {
    implementation(project(Modules.Feature.FeatureA.NAVIGATION_API))
    implementation(project(Modules.Feature.Login.DI_API))
    implementation(project(Modules.Feature.Login.DI_IMPL))
    implementation(project(Modules.Feature.FeatureA.DI_API))
    implementation(project(Modules.Feature.FeatureA.DI_IMPL))
}