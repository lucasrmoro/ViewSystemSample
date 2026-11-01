package br.com.viewsystem_sample.structural.push_notifications

/** Provides the Firebase Cloud Messaging registration token for this app instance. */
interface PushTokenProvider {

    /** Returns the current token, or null when Firebase is not configured. Firebase failures are propagated. */
    suspend fun getToken(): String?

}
