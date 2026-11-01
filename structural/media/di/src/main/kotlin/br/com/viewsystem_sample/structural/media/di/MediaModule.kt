package br.com.viewsystem_sample.structural.media.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.media.createMediaStorageProvider
import br.com.viewsystem_sample.structural.media.MediaStorageProvider
import br.com.viewsystem_sample.structural.permissions.PermissionQualifiers

object MediaModule {

    private val mediaModule = dependencyModule {
        singleton<MediaStorageProvider> {
            createMediaStorageProvider(
                context = get(),
                permissionsProvider = get(qualifier = PermissionQualifiers.ApplicationContext)
            )
        }
    }

    fun init() {
        loadModules(mediaModule)
    }

}
