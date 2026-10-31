package br.com.viewsystem_sample.structural.designsystem.biometric

import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import kotlinx.coroutines.CoroutineScope

fun createLifecycleBiometricProvider(delegate: BiometricProvider, screenScope: CoroutineScope): BiometricProvider =
    LifecycleBiometricProvider(delegate, screenScope)
