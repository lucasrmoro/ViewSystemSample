package br.com.viewsystem_sample.di

import br.com.viewsystem_sample.featureA.FeatureADI
import br.com.viewsystem_sample.featureA.FeatureADIImpl
import br.com.viewsystem_sample.core.di.CoreModule
import br.com.viewsystem_sample.core_network.di.CoreNetworkModule
import br.com.viewsystem_sample.local_storage.di.LocalStorageModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal object AppDI {

    private val featuresModule = module {
        single<FeatureADI> { FeatureADIImpl() }
    }

    fun load() {
        CoreModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
        loadKoinModules(featuresModule)
    }

}