import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.data)
}

dependencies {
    implementation(project(Modules.Feature.Login.DOMAIN_API))
    implementation(project(Modules.Feature.Login.DATA_API))
}
