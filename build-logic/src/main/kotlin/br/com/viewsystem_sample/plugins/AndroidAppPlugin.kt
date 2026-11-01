package br.com.viewsystem_sample.plugins

import br.com.viewsystem_sample.Plugins
import br.com.viewsystem_sample.ProjectConfig
import br.com.viewsystem_sample.ext.implementation
import br.com.viewsystem_sample.modules.Modules
import br.com.viewsystem_sample.plugins.base.BasePlugin
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidAppPlugin : BasePlugin() {

    override fun setup(project: Project) = with(project) {
        setupPlugins()
        setupConfig()
        setupDependencies()
    }

    private fun Project.setupPlugins() {
        apply(plugin = Plugins.ANDROID_APPLICATION)
        apply(plugin = Plugins.KOTLIN_ANDROID)
    }

    private fun Project.setupConfig() = extensions.configure<ApplicationExtension> {
        setupProjectConfig(this)
        defaultConfig.apply {
            applicationId = ProjectConfig.APP_ID
            targetSdk = ProjectConfig.TARGET_SDK
            versionCode = ProjectConfig.VERSION_CODE
            versionName = ProjectConfig.VERSION_NAME
        }
    }

    private fun Project.setupDependencies() = dependencies {
        implementation(project(Modules.Structural.NAVIGATION_API))
        implementation(project(Modules.Structural.NAVIGATION_DI))
        implementation(project(Modules.Structural.ANALYTICS_DI))
        implementation(project(Modules.Structural.REMOTE_CONFIG_DI))
        implementation(project(Modules.Structural.NOTIFICATIONS_DI))
        implementation(project(Modules.Structural.PUSH_NOTIFICATIONS_DI))
        implementation(project(Modules.Structural.CORE))
        implementation(project(Modules.Structural.CORE_DI))
        implementation(project(Modules.Structural.BIOMETRIC_API))
        implementation(project(Modules.Structural.BIOMETRIC_DI))
        implementation(project(Modules.Structural.SESSION_API))
        implementation(project(Modules.Structural.SESSION_DI))
        implementation(project(Modules.Structural.PERMISSIONS_API))
        implementation(project(Modules.Structural.PERMISSIONS_DI))
        implementation(project(Modules.Structural.NETWORK_API))
        implementation(project(Modules.Structural.NETWORK_DI))
        implementation(project(Modules.Structural.LOCAL_STORAGE_API))
        implementation(project(Modules.Structural.LOCAL_STORAGE_DI))
        implementation(project(Modules.Structural.DESIGN_SYSTEM))
        implementation(project(Modules.Structural.DESIGN_SYSTEM_DI))
        implementation(libs.material)
        implementation(libs.bundles.android)
        implementation(project(Modules.Structural.DI_API))
        implementation(project(Modules.Structural.LOGGER_API))
        implementation(project(Modules.Structural.LOGGER_DI))
    }
}
