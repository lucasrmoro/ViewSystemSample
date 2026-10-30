package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.network.impl.BuildConfig
import br.com.viewsystem_sample.structural.network.authenticator.NetworkAuthenticator
import br.com.viewsystem_sample.structural.network.ext.setConverterFactory
import br.com.viewsystem_sample.structural.network.ext.setTimeouts
import br.com.viewsystem_sample.structural.network.interceptor.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

private val retrofitModule = module {
    single<NetworkInterceptor> { NetworkInterceptor(preferencesDataSource = get()) }
    single<NetworkAuthenticator> {
        NetworkAuthenticator(
            appSessionProvider = get(),
            loginRepository = get(),
            preferencesDataSource = get()
        )
    }
    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
        }
    }
    single<OkHttpClient>(OkHttpClientAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<NetworkInterceptor>())
            .authenticator(get<NetworkAuthenticator>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    single<Retrofit>(RetrofitAuth) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientAuth))
            .build()
    }
    single<OkHttpClient>(OkHttpClientNoAuth) {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .setTimeouts()
            .build()
    }
    single<Retrofit>(RetrofitNoAuth) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .setConverterFactory()
            .client(get<OkHttpClient>(OkHttpClientNoAuth))
            .build()
    }
}

object CoreNetworkModule {
    fun init() {
        loadKoinModules(retrofitModule)
    }
}