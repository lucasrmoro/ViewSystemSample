package br.com.viewsystem_sample.structural.biometric.di

import androidx.fragment.app.FragmentActivity
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.biometric.BiometricProviderFactory
import br.com.viewsystem_sample.structural.core.ext.dispatcherMain
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton

object BiometricModule {

    private val biometricModule = dependencyModule {
        singleton { BiometricProviderFactory(mainDispatcher = dispatcherMain) }
        factory<BiometricProvider> { parameters ->
            get<BiometricProviderFactory>().create(parameters.get<FragmentActivity>())
        }
    }

    fun init() {
        loadModules(biometricModule)
    }

}
