package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.network.connectivity.NetworkConnectivityProvider
import br.com.viewsystem_sample.structural.network.createNetworkAuthenticator
import br.com.viewsystem_sample.structural.network.createNetworkConnectivityProvider
import br.com.viewsystem_sample.structural.network.ext.setConverterFactory
import br.com.viewsystem_sample.structural.network.ext.setTimeouts
import br.com.viewsystem_sample.structural.network.impl.BuildConfig as NetworkBuildConfig
import br.com.viewsystem_sample.structural.network.interceptor.NetworkInterceptor
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit

private val networkModule = dependencyModule {
    singleton<NetworkConnectivityProvider> { createNetworkConnectivityProvider(context = get()) }
    singleton<NetworkInterceptor> { NetworkInterceptor(preferencesDataSource = get()) }
    singleton<Authenticator> {
        createNetworkAuthenticator(
            appSessionProvider = get(),
            loginRepository = get(),
            preferencesDataSource = get()
        )
    }
    singleton<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (NetworkBuildConfig.DEBUG) Level.BODY else Level.NONE
        }
    }
    singleton<OkHttpClient>(OkHttpClientAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<NetworkInterceptor>())
            .authenticator(get<Authenticator>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    singleton<Retrofit>(RetrofitAuth) {
        Retrofit.Builder()
            .baseUrl(NetworkBuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientAuth))
            .build()
    }
    singleton<OkHttpClient>(OkHttpClientNoAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    singleton<Retrofit>(RetrofitNoAuth) {
        Retrofit.Builder()
            .baseUrl(NetworkBuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientNoAuth))
            .build()
    }
}

object CoreNetworkModule {
    fun init() {
        loadModules(networkModule)
    }
}
