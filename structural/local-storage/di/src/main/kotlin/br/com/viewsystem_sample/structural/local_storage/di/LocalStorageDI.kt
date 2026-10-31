package br.com.viewsystem_sample.structural.local_storage.di

import br.com.viewsystem_sample.featureA.dao.SomeDao
import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.structural.local_storage.LocalStorageFactory
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataSource
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val localStorageModule = module {
    single { LocalStorageFactory(context = get(), ioDispatcher = dispatcherIO) }
    single<PreferencesDataSource> { get<LocalStorageFactory>().createPreferencesDataSource() }
    factory<SomeDao> { get<LocalStorageFactory>().someDao() }
}

object LocalStorageModule {

    fun init() {
        loadKoinModules(localStorageModule)
    }

}
