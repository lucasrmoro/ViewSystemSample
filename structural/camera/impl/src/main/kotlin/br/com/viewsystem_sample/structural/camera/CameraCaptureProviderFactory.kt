package br.com.viewsystem_sample.structural.camera

import br.com.viewsystem_sample.structural.media.MediaStorageProvider

/** Creates a camera provider backed by the shared media storage service. */
fun createCameraCaptureProvider(storageProvider: MediaStorageProvider): CameraCaptureProvider =
    CameraCaptureProviderImpl(storageProvider)
