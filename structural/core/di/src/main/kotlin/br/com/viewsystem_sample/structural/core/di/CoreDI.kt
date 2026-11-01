package br.com.viewsystem_sample.structural.core.di

import br.com.viewsystem_sample.structural.core.provider.appState.AppStateProvider
import br.com.viewsystem_sample.structural.core.provider.appState.createAppStateProvider
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

private val coreModule = dependencyModule {
    singleton<CoroutineDispatcher>(DispatcherIO) { Dispatchers.IO }
    singleton<CoroutineDispatcher>(DispatcherMain) { Dispatchers.Main }
    singleton<CoroutineDispatcher>(DispatcherDefault) { Dispatchers.Default }
    singleton<AppStateProvider> {
        createAppStateProvider(appSessionProvider = get(), networkConnectivityProvider = get())
    }
}

object CoreModule {

    fun init() {
        loadModules(coreModule)
    }

}
