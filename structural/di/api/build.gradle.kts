plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(libs.androidx.activity.ktx)
    api(libs.androidx.fragment.ktx)
    api(libs.androidx.lifecycle.viewmodel.savedstate)
}
