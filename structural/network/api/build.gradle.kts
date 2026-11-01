import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(libs.androidx.lifecycle.livedata.core.ktx)
    api(libs.retrofit)
    api(project(Modules.Structural.DI_API))
    api(libs.coroutines)
    implementation(libs.gson)
}
