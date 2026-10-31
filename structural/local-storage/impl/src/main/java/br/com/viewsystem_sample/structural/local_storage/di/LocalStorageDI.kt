package br.com.viewsystem_sample.structural.local_storage.di

import androidx.room.Room
import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.featureA.dao.SomeDao
import br.com.viewsystem_sample.structural.local_storage.db.LocalDatabase
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataSource
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataSourceImpl
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataStoreFactory
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private const val ROOM_DATA_BASE_NAME = "sample_data_base"

private val localStorageModule = module {
    single<LocalDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = LocalDatabase::class.java,
            name = ROOM_DATA_BASE_NAME
        ).build()
    }
    factory<SomeDao> { get<LocalDatabase>().fooDao() }
    single<PreferencesDataStoreFactory> {
        PreferencesDataStoreFactory(context = get(), ioDispatcher = dispatcherIO)
    }
    single<PreferencesDataSource> {
        PreferencesDataSourceImpl(dataStoreFactory = get(), ioDispatcher = dispatcherIO)
    }
}

object LocalStorageModule {

    fun init() {
        loadKoinModules(localStorageModule)
    }

}
