package br.com.viewsystem_sample.structural.remote_config.di

import br.com.viewsystem_sample.structural.remote_config.RemoteConfigProvider
import br.com.viewsystem_sample.structural.remote_config.createRemoteConfigProvider
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object RemoteConfigModule {

    fun init() {
        loadKoinModules(module {
            single<RemoteConfigProvider> { createRemoteConfigProvider(context = get()) }
        })
    }

}
