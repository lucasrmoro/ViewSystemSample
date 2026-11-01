package br.com.viewsystem_sample.structural.media

import android.content.Context
import br.com.viewsystem_sample.structural.permissions.PermissionsProvider

fun createMediaStorageProvider(
    context: Context,
    permissionsProvider: PermissionsProvider
): MediaStorageProvider = MediaStorageProviderImpl(context, permissionsProvider)
