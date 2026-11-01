package br.com.viewsystem_sample.structural.navigation.di

import android.app.Activity
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.navigation.core.NavigationRegistry
import br.com.viewsystem_sample.structural.navigation.core.Navigator
import br.com.viewsystem_sample.structural.navigation.NavigatorFactory

private val navigationModule = dependencyModule {
    singleton { NavigatorFactory() }
    singleton<NavigationRegistry> { get<NavigatorFactory>().registry }
    factory<Navigator> { parameters ->
        get<NavigatorFactory>().create(parameters.get<Activity>())
    }
}

object NavigationModule {

    fun init() {
        loadModules(navigationModule)
    }

}
