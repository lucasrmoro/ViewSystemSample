package br.com.viewsystem_sample.structural.remote_config.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.remote_config.RemoteConfigProvider
import br.com.viewsystem_sample.structural.remote_config.createRemoteConfigProvider

object RemoteConfigModule {

    fun init() {
        loadModules(dependencyModule {
            singleton<RemoteConfigProvider> { createRemoteConfigProvider(context = get()) }
        })
    }

}
