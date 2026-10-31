package br.com.viewsystem_sample.di

import br.com.viewsystem_sample.featureA.FeatureAModule
import br.com.viewsystem_sample.login.LoginModule
import br.com.viewsystem_sample.structural.biometric.di.BiometricModule
import br.com.viewsystem_sample.structural.core.di.CoreModule
import br.com.viewsystem_sample.structural.designsystem.di.DesignSystemModule
import br.com.viewsystem_sample.structural.local_storage.di.LocalStorageModule
import br.com.viewsystem_sample.structural.logger.di.LoggerModule
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.permissions.di.PermissionsModule
import br.com.viewsystem_sample.structural.session.di.SessionModule

internal object AppDI {

    fun load() {
        LoggerModule.init()
        CoreModule.init()
        SessionModule.init()
        PermissionsModule.init()
        BiometricModule.init()
        DesignSystemModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
        LoginModule.init()
        FeatureAModule.init()
    }

}
