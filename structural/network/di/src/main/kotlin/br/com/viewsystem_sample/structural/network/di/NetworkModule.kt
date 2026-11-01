package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.network.connectivity.NetworkConnectivityProvider
import br.com.viewsystem_sample.structural.network.createNetworkAuthenticator
import br.com.viewsystem_sample.structural.network.createNetworkConnectivityProvider
import br.com.viewsystem_sample.structural.network.createNetworkInterceptor
import br.com.viewsystem_sample.structural.network.http.HttpClient
import br.com.viewsystem_sample.structural.network.http.HttpClientConfiguration
import br.com.viewsystem_sample.structural.network.http.HttpClientFactory
import br.com.viewsystem_sample.structural.network.impl.BuildConfig as NetworkBuildConfig
import okhttp3.Authenticator
import okhttp3.Interceptor

object NetworkModule {

    private val networkModule = dependencyModule {
        singleton { HttpClientConfiguration(baseUrl = NetworkBuildConfig.API_BASE_URL) }
        singleton<NetworkConnectivityProvider> { createNetworkConnectivityProvider(context = get()) }
        singleton<Authenticator> {
            createNetworkAuthenticator(
                appSessionProvider = get(),
                loginRepository = get(),
                preferencesDataSource = get()
            )
        }
        singleton<Interceptor> { createNetworkInterceptor(preferencesDataSource = get()) }
        singleton { HttpClientFactory() }
        singleton<HttpClient>(AuthenticatedHttpClient) {
            get<HttpClientFactory>().createAuthenticated(
                baseUrl = get<HttpClientConfiguration>().baseUrl,
                dispatcher = dispatcherIO,
                authenticator = get(),
                interceptor = get()
            )
        }
        singleton<HttpClient>(UnauthenticatedHttpClient) {
            get<HttpClientFactory>().createUnauthenticated(
                baseUrl = get<HttpClientConfiguration>().baseUrl,
                dispatcher = dispatcherIO
            )
        }
    }

    fun init() {
        loadModules(networkModule)
    }

}
