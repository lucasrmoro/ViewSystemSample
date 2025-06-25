plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    implementation(libs.gson)
    implementation(libs.bundles.room)
}