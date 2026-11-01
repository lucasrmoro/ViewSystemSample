package br.com.viewsystem_sample.structural.session.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.session.AppSessionProvider
import br.com.viewsystem_sample.structural.session.createAppSessionProvider

private val sessionModule = dependencyModule {
    singleton<AppSessionProvider> { createAppSessionProvider() }
}

object SessionModule {

    fun init() {
        loadModules(sessionModule)
    }

}
