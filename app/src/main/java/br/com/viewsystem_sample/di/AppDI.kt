package br.com.viewsystem_sample.di

import br.com.viewsystem_sample.featureA.FeatureADI
import br.com.viewsystem_sample.featureA.FeatureADIImpl
import br.com.viewsystem_sample.login.LoginDI
import br.com.viewsystem_sample.login.LoginDIImpl
import br.com.viewsystem_sample.structural.core.di.CoreModule
import br.com.viewsystem_sample.structural.local_storage.di.LocalStorageModule
import br.com.viewsystem_sample.structural.logger.di.LoggerModule
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.permissions.di.PermissionsModule
import br.com.viewsystem_sample.structural.session.di.SessionModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal object AppDI {

    private val featuresModule = module(createdAtStart = true) {
        single<FeatureADI> { FeatureADIImpl().apply { load() } }
        single<LoginDI> { LoginDIImpl().apply { load() } }
    }

    fun load() {
        LoggerModule.init()
        CoreModule.init()
        SessionModule.init()
        PermissionsModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
        loadKoinModules(featuresModule)
    }

}