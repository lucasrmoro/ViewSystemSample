package br.com.viewsystem_sample.structural.push_notifications

import br.com.viewsystem_sample.structural.notifications.NotificationProvider

fun createPushMessageHandler(notificationProvider: NotificationProvider): PushMessageHandler =
    PushMessageHandlerImpl(notificationProvider)
