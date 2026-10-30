package br.com.viewsystem_sample.structural.core.di

import br.com.viewsystem_sample.structural.core.provider.appState.AppStateProvider
import br.com.viewsystem_sample.structural.core.provider.appState.AppStateProviderImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val coreModule = module {
    single<CoroutineDispatcher>(DispatcherIO) { Dispatchers.IO }
    single<CoroutineDispatcher>(DispatcherMain) { Dispatchers.Main }
    single<CoroutineDispatcher>(DispatcherDefault) { Dispatchers.Default }
    single<AppStateProvider> {
        AppStateProviderImpl(appSessionProvider = get(), networkConnectivityProvider = get())
    }
}

object CoreModule {
    fun init() {
        loadKoinModules(coreModule)
    }
}