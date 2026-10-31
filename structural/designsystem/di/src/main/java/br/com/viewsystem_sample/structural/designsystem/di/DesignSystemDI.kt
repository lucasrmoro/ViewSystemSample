package br.com.viewsystem_sample.structural.designsystem.di

import androidx.fragment.app.FragmentActivity
import br.com.viewsystem_sample.structural.biometric.BiometricProvider
import br.com.viewsystem_sample.structural.designsystem.biometric.createLifecycleBiometricProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

private val designSystemModule = module {
    factory<BiometricProvider>(ScreenBiometricProvider) { parameters ->
        createLifecycleBiometricProvider(
            delegate = get { parametersOf(parameters.get<FragmentActivity>()) },
            screenScope = parameters.get<CoroutineScope>()
        )
    }
}

object DesignSystemModule {

    fun init() {
        loadKoinModules(designSystemModule)
    }

}
