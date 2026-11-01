import br.com.viewsystem_sample.modules.Modules
import br.com.viewsystem_sample.modules.Modules.Feature.FeatureA

plugins {
    alias(libs.plugins.sample.di)
}

dependencies {
    implementation(project(FeatureA.ANALYTICS))
    implementation(project(Modules.Structural.NAVIGATION_API))
    implementation(project(FeatureA.DATA))
    implementation(project(FeatureA.DOMAIN_API))
    implementation(project(FeatureA.DOMAIN_IMPL))
    implementation(project(FeatureA.PRESENTATION))
}