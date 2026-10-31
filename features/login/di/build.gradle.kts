import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.di)
}

dependencies {
    implementation(project(Modules.Feature.Login.DOMAIN_API))
    testImplementation(libs.junit)
    testImplementation(libs.okhttp.mockwebserver)
    testImplementation(project(Modules.Structural.NETWORK_DI))
    implementation(project(Modules.Feature.Login.DATA))
}
