package br.com.viewsystem_sample.plugins

import br.com.viewsystem_sample.Plugins
import br.com.viewsystem_sample.ext.implementation
import br.com.viewsystem_sample.ext.ksp
import br.com.viewsystem_sample.modules.Modules
import br.com.viewsystem_sample.plugins.base.BaseLibraryPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class DomainPlugin : BaseLibraryPlugin() {

    override fun setupPlugins(project: Project) = with(project) {
        apply(plugin = Plugins.ANDROID_LIBRARY)
        apply(plugin = Plugins.KOTLIN_ANDROID)
        apply(plugin = Plugins.KSP)
    }

    override fun setupDependencies(project: Project) = with(project) {
        dependencies {
            implementation(project(Modules.Structural.CORE))
            implementation(project(Modules.Structural.LOGGER_API))
            ksp(libs.koin.compiler)
            implementation(libs.bundles.koin)
        }
    }

}