import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(libs.gson)
    implementation(libs.bundles.room)
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
}