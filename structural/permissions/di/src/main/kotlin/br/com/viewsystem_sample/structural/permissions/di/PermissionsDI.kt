package br.com.viewsystem_sample.structural.permissions.di

import androidx.activity.ComponentActivity
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.permissions.PermissionsProvider
import br.com.viewsystem_sample.structural.permissions.createPermissionsProvider

object PermissionsModule {

    private val permissionsModule = dependencyModule {
        factory<PermissionsProvider> { params ->
            createPermissionsProvider(activity = params.get<ComponentActivity>())
        }
    }

    fun init() {
        loadModules(permissionsModule)
    }

}
