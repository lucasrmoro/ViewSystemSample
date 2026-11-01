package br.com.viewsystem_sample.structural.media_compression.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.structural.media.MediaStorageProvider
import br.com.viewsystem_sample.structural.media_compression.api.MediaCompressionProvider
import br.com.viewsystem_sample.structural.media_compression.impl.createMediaCompressionProvider

object MediaCompressionModule {

    private val mediaCompressionModule = dependencyModule {
        singleton<MediaCompressionProvider> {
            createMediaCompressionProvider(
                context = get(),
                storageProvider = get<MediaStorageProvider>(),
                ioDispatcher = dispatcherIO
            )
        }
    }

    fun init() {
        loadModules(mediaCompressionModule)
    }

}
