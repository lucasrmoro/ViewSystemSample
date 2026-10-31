package br.com.viewsystem_sample.app

import android.app.Application
import br.com.viewsystem_sample.featureA.FeatureAModule
import br.com.viewsystem_sample.login.LoginModule
import br.com.viewsystem_sample.structural.analytics.initializeAnalytics
import br.com.viewsystem_sample.structural.analytics.initializeFirebase
import br.com.viewsystem_sample.structural.analytics.di.AnalyticsModule
import br.com.viewsystem_sample.structural.biometric.di.BiometricModule
import br.com.viewsystem_sample.structural.core.di.CoreModule
import br.com.viewsystem_sample.structural.core.di.DispatcherIO
import br.com.viewsystem_sample.structural.designsystem.di.DesignSystemModule
import br.com.viewsystem_sample.structural.local_storage.di.LocalStorageModule
import br.com.viewsystem_sample.structural.logger.di.LoggerModule
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.permissions.di.PermissionsModule
import br.com.viewsystem_sample.structural.remote_config.di.RemoteConfigModule
import br.com.viewsystem_sample.structural.remote_config.initializeRemoteConfig
import br.com.viewsystem_sample.structural.session.di.SessionModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeFirebase(this)
        initializeAnalytics(this)
        val koin = setupKoin()
        val applicationScope = CoroutineScope(SupervisorJob() + koin.get<CoroutineDispatcher>(DispatcherIO))
        applicationScope.launch {
            initializeRemoteConfig(context = this@AppApplication, logger = koin.get())
        }
    }

    private fun setupKoin(): Koin = startKoin {
        androidContext(applicationContext)
        setupStructuralModules()
        setupFeatureModules()
    }.koin

    private fun setupStructuralModules() {
        LoggerModule.init()
        CoreModule.init()
        AnalyticsModule.init()
        RemoteConfigModule.init()
        SessionModule.init()
        PermissionsModule.init()
        BiometricModule.init()
        DesignSystemModule.init()
        CoreNetworkModule.init()
        LocalStorageModule.init()
    }

    private fun setupFeatureModules() {
        LoginModule.init()
        FeatureAModule.init()
    }

}
