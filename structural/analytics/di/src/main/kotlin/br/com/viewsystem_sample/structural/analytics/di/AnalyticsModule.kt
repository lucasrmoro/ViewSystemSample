package br.com.viewsystem_sample.structural.analytics.di

import br.com.viewsystem_sample.structural.analytics.AnalyticsProvider
import br.com.viewsystem_sample.structural.analytics.createAnalyticsProvider
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton

private val analyticsModule = dependencyModule {
    singleton<AnalyticsProvider> { createAnalyticsProvider(context = get()) }
}

object AnalyticsModule {

    fun init() {
        loadModules(analyticsModule)
    }

}
