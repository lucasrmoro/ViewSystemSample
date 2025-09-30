import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.domain)
}

dependencies {
    implementation(project(Modules.Feature.FeatureA.DOMAIN_API))
}