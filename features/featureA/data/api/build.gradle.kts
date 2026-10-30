import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(libs.gson)
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
}