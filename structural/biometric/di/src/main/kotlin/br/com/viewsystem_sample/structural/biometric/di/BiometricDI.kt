package br.com.viewsystem_sample.structural.biometric.di

import androidx.fragment.app.FragmentActivity
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.biometric.BiometricProviderFactory
import br.com.viewsystem_sample.structural.core.ext.dispatcherMain
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val biometricModule = module {
    single { BiometricProviderFactory(mainDispatcher = dispatcherMain) }
    factory<BiometricProvider> { parameters ->
        get<BiometricProviderFactory>().create(parameters.get<FragmentActivity>())
    }
}

object BiometricModule {

    fun init() {
        loadKoinModules(biometricModule)
    }

}
