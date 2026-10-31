package br.com.viewsystem_sample.structural.biometric

/** Opaque value ready to persist as a string and pass back to authenticate. */
data class BiometricEncryptedData(
    val value: String
)
