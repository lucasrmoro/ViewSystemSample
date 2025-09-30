package br.com.viewsystem_sample.plugins

import br.com.viewsystem_sample.Plugins
import br.com.viewsystem_sample.ProjectConfig
import br.com.viewsystem_sample.ext.implementation
import br.com.viewsystem_sample.ext.ksp
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
        apply(plugin = Plugins.KSP)
        apply(plugin = Plugins.QUADRANT)
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
        implementation(project(Modules.CORE))
        implementation(project(Modules.CORE_NETWORK))
        implementation(project(Modules.LOCAL_STORAGE))
        implementation(project(Modules.CORE_UI))
        implementation(libs.material)
        implementation(libs.bundles.android)
        implementation(libs.bundles.koin)
        implementation(libs.timber)
        ksp(libs.koin.compiler)
    }
}