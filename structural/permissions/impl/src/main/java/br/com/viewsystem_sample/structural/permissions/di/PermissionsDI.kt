package br.com.viewsystem_sample.structural.permissions.di

import androidx.activity.ComponentActivity
import br.com.viewsystem_sample.structural.permissions.PermissionsProvider
import br.com.viewsystem_sample.structural.permissions.PermissionsProviderImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val permissionsModule = module {
    factory<PermissionsProvider> { params ->
        PermissionsProviderImpl(activity = params.get<ComponentActivity>())
    }
}

object PermissionsModule {

    fun init() {
        loadKoinModules(permissionsModule)
    }

}
