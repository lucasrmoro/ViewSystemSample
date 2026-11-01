import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(libs.androidx.activity.ktx)
    api(project(Modules.Structural.Media.API))
}
