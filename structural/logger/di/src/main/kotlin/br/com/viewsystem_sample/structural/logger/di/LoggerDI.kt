package br.com.viewsystem_sample.structural.logger.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.logger.Logger
import br.com.viewsystem_sample.structural.logger.createLogger

object LoggerModule {

    fun init() {
        loadModules(dependencyModule {
            singleton<Logger> { createLogger() }
        })
    }

}
