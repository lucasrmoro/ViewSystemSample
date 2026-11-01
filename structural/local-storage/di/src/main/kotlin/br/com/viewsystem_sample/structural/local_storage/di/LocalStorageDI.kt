package br.com.viewsystem_sample.structural.local_storage.di

import br.com.viewsystem_sample.featureA.dao.SomeDao
import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.local_storage.LocalStorageFactory
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataSource

private val localStorageModule = dependencyModule {
    singleton { LocalStorageFactory(context = get(), ioDispatcher = dispatcherIO) }
    singleton<PreferencesDataSource> { get<LocalStorageFactory>().createPreferencesDataSource() }
    factory<SomeDao> { get<LocalStorageFactory>().someDao() }
}

object LocalStorageModule {

    fun init() {
        loadModules(localStorageModule)
    }

}
