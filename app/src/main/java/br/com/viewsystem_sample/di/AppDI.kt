package br.com.viewsystem_sample.di

import br.com.viewsystem_sample.featureA.FeatureADI
import br.com.viewsystem_sample.featureA.FeatureADIImpl
import br.com.viewsystem_sample.login.LoginDI
import br.com.viewsystem_sample.login.LoginDIImpl
import br.com.viewsystem_sample.structural.core.di.CoreModule
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.local_storage.di.LocalStorageModule
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal object AppDI {

    private val featuresModule = module {
        single<FeatureADI> { FeatureADIImpl() }
        single<LoginDI> { LoginDIImpl() }
    }

    fun load() {
        CoreModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
        loadKoinModules(featuresModule)
        GlobalContext.get().get<LoginDI>().load()
    }

}