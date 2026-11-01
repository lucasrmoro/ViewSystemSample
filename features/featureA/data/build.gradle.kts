import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.data)
}

dependencies {
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
    implementation(libs.bundles.room)
}
