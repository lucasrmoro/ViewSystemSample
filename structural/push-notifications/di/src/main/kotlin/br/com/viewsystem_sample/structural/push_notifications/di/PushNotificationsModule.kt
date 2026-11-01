package br.com.viewsystem_sample.structural.push_notifications.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.push_notifications.PushMessageHandler
import br.com.viewsystem_sample.structural.push_notifications.PushTokenProvider
import br.com.viewsystem_sample.structural.push_notifications.createPushMessageHandler
import br.com.viewsystem_sample.structural.push_notifications.createPushTokenProvider

object PushNotificationsModule {

    private val pushNotificationsModule = dependencyModule {
        singleton<PushTokenProvider> { createPushTokenProvider(context = get()) }
        singleton<PushMessageHandler> { createPushMessageHandler(notificationProvider = get()) }
    }

    fun init() {
        loadModules(pushNotificationsModule)
    }

}
