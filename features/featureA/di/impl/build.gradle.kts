import br.com.viewsystem_sample.modules.Modules.Feature.FeatureA

plugins {
    alias(libs.plugins.sample.di)
}

dependencies {
    implementation(project(FeatureA.NAVIGATION_API))
    implementation(project(FeatureA.DI_API))
    implementation(project(FeatureA.DATA))
    implementation(project(FeatureA.DOMAIN_API))
    implementation(project(FeatureA.DOMAIN_IMPL))
    implementation(project(FeatureA.PRESENTATION))
}