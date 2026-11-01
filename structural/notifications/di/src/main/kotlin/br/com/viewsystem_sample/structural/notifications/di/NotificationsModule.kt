package br.com.viewsystem_sample.structural.notifications.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.notifications.NotificationProvider
import br.com.viewsystem_sample.structural.notifications.createNotificationProvider
import br.com.viewsystem_sample.structural.permissions.PermissionQualifiers
import br.com.viewsystem_sample.structural.permissions.PermissionsProvider

object NotificationsModule {

    private val notificationsModule = dependencyModule {
        singleton<NotificationProvider> {
            createNotificationProvider(
                context = get(),
                permissionsProvider = get<PermissionsProvider>(qualifier = PermissionQualifiers.ApplicationContext)
            )
        }
    }

    fun init() {
        loadModules(notificationsModule)
    }

}
