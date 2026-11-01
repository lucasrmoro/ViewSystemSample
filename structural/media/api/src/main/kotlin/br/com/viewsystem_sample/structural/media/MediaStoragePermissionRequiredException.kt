package br.com.viewsystem_sample.structural.media

import java.io.IOException

/** Signals that writing to legacy gallery storage needs a runtime permission. */
class MediaStoragePermissionRequiredException : IOException(
    "External storage permission is required to write to the gallery"
)
