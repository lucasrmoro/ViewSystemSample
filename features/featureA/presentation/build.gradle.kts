import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.presentation)
}

dependencies {
    implementation(project(Modules.Feature.FeatureA.ANALYTICS))
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
    implementation(project(Modules.Structural.Navigation.API))
}