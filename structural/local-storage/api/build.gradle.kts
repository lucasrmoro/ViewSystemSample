import br.com.viewsystem_sample.modules.Modules

plugins {
    alias(libs.plugins.sample.android.library)
}

dependencies {
    api(project(Modules.Structural.BIOMETRIC_API))
}
