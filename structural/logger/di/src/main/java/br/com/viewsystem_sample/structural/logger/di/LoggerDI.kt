package br.com.viewsystem_sample.structural.logger.di

import br.com.viewsystem_sample.structural.logger.Logger
import br.com.viewsystem_sample.structural.logger.createLogger
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object LoggerModule {

    fun init() {
        loadKoinModules(module {
            single<Logger> { createLogger() }
        })
    }

}
