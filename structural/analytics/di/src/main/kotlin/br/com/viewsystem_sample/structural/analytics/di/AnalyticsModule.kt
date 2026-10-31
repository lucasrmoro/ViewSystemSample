package br.com.viewsystem_sample.structural.analytics.di

import br.com.viewsystem_sample.structural.analytics.AnalyticsProvider
import br.com.viewsystem_sample.structural.analytics.createAnalyticsProvider
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AnalyticsModule {

    fun init() {
        loadKoinModules(module {
            single<AnalyticsProvider> { createAnalyticsProvider(context = get()) }
        })
    }

}
