package br.com.viewsystem_sample.structural.local_storage.prefs

import br.com.viewsystem_sample.structural.biometric.BiometricDecryption
import br.com.viewsystem_sample.structural.biometric.BiometricEncryptedData
import br.com.viewsystem_sample.structural.biometric.BiometricResult

/** An immutable snapshot of the stored package, read before biometric authentication. */
interface EncryptedPreferences {

    /** The stored package to pass unchanged to BiometricProvider.authenticate. */
    val data: BiometricEncryptedData

    /**
     * Consumes [decryption] to reconstruct the native values from this snapshot, without reading storage again.
     *
     * @return Caller-owned values that must be closed after use, or a decryption failure.
     * @throws IllegalStateException If [decryption] has already been consumed.
     */
    suspend fun decrypt(decryption: BiometricDecryption): BiometricResult<DecryptedPreferences>

}
