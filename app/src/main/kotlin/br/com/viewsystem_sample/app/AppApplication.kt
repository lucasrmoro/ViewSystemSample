package br.com.viewsystem_sample.app

import android.app.Application
import br.com.viewsystem_sample.featureA.FeatureAModule
import br.com.viewsystem_sample.login.LoginModule
import br.com.viewsystem_sample.structural.analytics.di.AnalyticsModule
import br.com.viewsystem_sample.structural.analytics.initializeAnalytics
import br.com.viewsystem_sample.structural.analytics.initializeFirebase
import br.com.viewsystem_sample.structural.biometric.di.BiometricModule
import br.com.viewsystem_sample.structural.camera.di.CameraModule
import br.com.viewsystem_sample.structural.media.di.MediaModule
import br.com.viewsystem_sample.structural.media_compression.di.MediaCompressionModule
import br.com.viewsystem_sample.structural.core.di.CoreModule
import br.com.viewsystem_sample.structural.core.di.DispatcherIO
import br.com.viewsystem_sample.structural.designsystem.di.DesignSystemModule
import br.com.viewsystem_sample.structural.di.DependencyResolver
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.startDependencyInjection
import br.com.viewsystem_sample.structural.local_storage.di.LocalStorageModule
import br.com.viewsystem_sample.structural.logger.di.LoggerModule
import br.com.viewsystem_sample.structural.logger.Logger
import br.com.viewsystem_sample.structural.navigation.di.NavigationModule
import br.com.viewsystem_sample.structural.network.di.NetworkModule
import br.com.viewsystem_sample.structural.notifications.di.NotificationsModule
import br.com.viewsystem_sample.structural.permissions.di.PermissionsModule
import br.com.viewsystem_sample.structural.push_notifications.PushMessageHandler
import br.com.viewsystem_sample.structural.push_notifications.PushMessagingServiceHost
import br.com.viewsystem_sample.structural.push_notifications.di.PushNotificationsModule
import br.com.viewsystem_sample.structural.push_notifications.initializePushNotifications
import br.com.viewsystem_sample.structural.remote_config.di.RemoteConfigModule
import br.com.viewsystem_sample.structural.remote_config.initializeRemoteConfig
import br.com.viewsystem_sample.structural.session.di.SessionModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AppApplication : Application(), PushMessagingServiceHost {

    private lateinit var dependencyResolver: DependencyResolver

    override fun onCreate() {
        super.onCreate()
        initializeFirebase(this)
        initializeAnalytics(this)
        initializePushNotifications(this)
        dependencyResolver = setupDependencyInjection()
        val dependencies = dependencyResolver
        val applicationScope =
            CoroutineScope(SupervisorJob() + dependencies.get<CoroutineDispatcher>(DispatcherIO))
        applicationScope.launch {
            initializeRemoteConfig(context = this@AppApplication, logger = dependencies.get())
        }
    }

    private fun setupDependencyInjection(): DependencyResolver =
        startDependencyInjection(this) {
            setupStructuralModules()
            setupFeatureModules()
        }

    private fun setupStructuralModules() {
        NavigationModule.init()
        LoggerModule.init()
        CoreModule.init()
        AnalyticsModule.init()
        RemoteConfigModule.init()
        NotificationsModule.init()
        PushNotificationsModule.init()
        SessionModule.init()
        PermissionsModule.init()
        BiometricModule.init()
        MediaModule.init()
        CameraModule.init()
        MediaCompressionModule.init()
        DesignSystemModule.init()
        NetworkModule.init()
        LocalStorageModule.init()
    }

    private fun setupFeatureModules() {
        LoginModule.init()
        FeatureAModule.init(navigationRegistry = get())
    }

    override fun resolvePushMessageHandler(): PushMessageHandler = dependencyResolver.get()

    override fun resolveLogger(): Logger = dependencyResolver.get()

}
