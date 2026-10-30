import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.data)
}

dependencies {
    implementation(libs.bundles.room)
    implementation(project(Modules.Feature.FeatureA.DATA_API))
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
}