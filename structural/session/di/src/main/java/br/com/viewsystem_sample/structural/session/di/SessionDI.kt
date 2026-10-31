package br.com.viewsystem_sample.structural.session.di

import br.com.viewsystem_sample.structural.session.AppSessionProvider
import br.com.viewsystem_sample.structural.session.createAppSessionProvider
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val sessionModule = module {
    single<AppSessionProvider> { createAppSessionProvider() }
}

object SessionModule {

    fun init() {
        loadKoinModules(sessionModule)
    }

}
