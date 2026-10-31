package br.com.viewsystem_sample.structural.local_storage.prefs

class EncryptedPreferenceEntry<T : Any> internal constructor(
    val preference: EncryptedPreferenceType<T>,
    val value: T
)
