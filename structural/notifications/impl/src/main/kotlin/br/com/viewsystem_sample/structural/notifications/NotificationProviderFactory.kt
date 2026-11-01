package br.com.viewsystem_sample.structural.notifications

import android.content.Context
import br.com.viewsystem_sample.structural.permissions.PermissionsProvider

fun createNotificationProvider(
    context: Context,
    permissionsProvider: PermissionsProvider
): NotificationProvider = NotificationProviderImpl(context.applicationContext, permissionsProvider)
