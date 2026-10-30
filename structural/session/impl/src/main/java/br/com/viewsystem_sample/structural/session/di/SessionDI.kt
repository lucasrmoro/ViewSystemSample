package br.com.viewsystem_sample.structural.session.di

import br.com.viewsystem_sample.structural.session.AppSessionProvider
import br.com.viewsystem_sample.structural.session.AppSessionProviderImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val sessionModule = module {
    single<AppSessionProvider> { AppSessionProviderImpl() }
}

object SessionModule {

    fun init() {
        loadKoinModules(sessionModule)
    }

}
