plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(libs.retrofit)
    api(libs.koin.core)
    api(libs.coroutines)
    implementation(libs.gson)
}
