package br.com.viewsystem_sample.structural.designsystem.biometric

import br.com.viewsystem_sample.structural.biometric.BiometricDecryption
import br.com.viewsystem_sample.structural.biometric.BiometricEncryption
import br.com.viewsystem_sample.structural.biometric.BiometricEncryptedData
import br.com.viewsystem_sample.structural.biometric.BiometricPromptConfig
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.biometric.BiometricResult
import br.com.viewsystem_sample.structural.biometric.BiometricStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

internal class LifecycleBiometricProvider(
    private val delegate: BiometricProvider,
    private val screenScope: CoroutineScope
) : BiometricProvider {

    override fun getStatus(): BiometricStatus = delegate.getStatus()

    override suspend fun enroll(
        prompt: BiometricPromptConfig
    ): BiometricResult<BiometricEncryption> = onScreen { delegate.enroll(prompt) }

    override suspend fun authenticate(
        data: BiometricEncryptedData,
        prompt: BiometricPromptConfig
    ): BiometricResult<BiometricDecryption> = onScreen { delegate.authenticate(data, prompt) }

    private suspend fun <T> onScreen(operation: suspend () -> T): T {
        val request = screenScope.async { operation() }
        return try {
            request.await()
        } finally {
            request.cancel()
        }
    }

}
