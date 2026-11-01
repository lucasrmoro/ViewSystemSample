package br.com.viewsystem_sample.structural.camera.di

import br.com.viewsystem_sample.structural.camera.CameraCaptureProvider
import br.com.viewsystem_sample.structural.camera.createCameraCaptureProvider
import br.com.viewsystem_sample.structural.media.MediaStorageProvider
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton

object CameraModule {

    private val cameraModule = dependencyModule {
        singleton<CameraCaptureProvider> {
            createCameraCaptureProvider(
                storageProvider = get<MediaStorageProvider>()
            )
        }
    }

    fun init() {
        loadModules(cameraModule)
    }

}
