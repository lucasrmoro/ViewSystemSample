package br.com.viewsystem_sample.structural.push_notifications

/** Handles foreground push messages delivered by Firebase Cloud Messaging. */
interface PushMessageHandler {

    /** Displays available title and body through the local notification service. */
    fun handle(message: PushMessage)

}
