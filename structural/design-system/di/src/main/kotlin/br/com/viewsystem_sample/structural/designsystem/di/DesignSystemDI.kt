package br.com.viewsystem_sample.structural.designsystem.di

import androidx.fragment.app.FragmentActivity
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.designsystem.biometric.createLifecycleBiometricProvider
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.parametersOf
import kotlinx.coroutines.CoroutineScope

object DesignSystemModule {

    private val designSystemModule = dependencyModule {
        factory<BiometricProvider>(ScreenBiometricProvider) { parameters ->
            createLifecycleBiometricProvider(
                delegate = get { parametersOf(parameters.get<FragmentActivity>()) },
                screenScope = parameters.get<CoroutineScope>()
            )
        }
    }

    fun init() {
        loadModules(designSystemModule)
    }

}
