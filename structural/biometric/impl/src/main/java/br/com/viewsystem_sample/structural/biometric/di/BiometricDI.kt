package br.com.viewsystem_sample.structural.biometric.di

import androidx.fragment.app.FragmentActivity
import br.com.viewsystem_sample.structural.core.ext.dispatcherMain
import br.com.viewsystem_sample.structural.biometric.BiometricAuthenticator
import br.com.viewsystem_sample.structural.biometric.BiometricCipher
import br.com.viewsystem_sample.structural.biometric.BiometricKeyStore
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.biometric.BiometricProviderImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val biometricModule = module {
    single { BiometricKeyStore() }
    factory<BiometricProvider> { parameters ->
        BiometricProviderImpl(
            authenticator = BiometricAuthenticator(parameters.get<FragmentActivity>()),
            keyStore = get(),
            biometricCipher = BiometricCipher(),
            mainDispatcher = dispatcherMain
        )
    }
}

object BiometricModule {

    fun init() {
        loadKoinModules(biometricModule)
    }

}
