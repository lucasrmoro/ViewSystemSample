package br.com.viewsystem_sample.plugins

import br.com.viewsystem_sample.Plugins
import br.com.viewsystem_sample.ext.implementation
import br.com.viewsystem_sample.modules.Modules
import br.com.viewsystem_sample.plugins.base.BaseLibraryPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class DataPlugin : BaseLibraryPlugin() {

    override fun setupPlugins(project: Project) = with(project) {
        apply(plugin = Plugins.ANDROID_LIBRARY)
        apply(plugin = Plugins.KOTLIN_ANDROID)
        apply(plugin = Plugins.KSP)
    }

    override fun setupDependencies(project: Project) = with(project) {
        dependencies {
            implementation(project(Modules.Structural.Core.MAIN))
            implementation(project(Modules.Structural.Network.API))
            implementation(project(Modules.Structural.LocalStorage.API))
            implementation(libs.coroutines)
        }
    }

}
