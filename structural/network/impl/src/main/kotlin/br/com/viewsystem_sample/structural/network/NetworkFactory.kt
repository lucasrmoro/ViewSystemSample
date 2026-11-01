package br.com.viewsystem_sample.structural.network

import android.content.Context
import br.com.viewsystem_sample.login.repository.LoginRepository
import br.com.viewsystem_sample.structural.local_storage.prefs.PreferencesDataSource
import br.com.viewsystem_sample.structural.network.authenticator.NetworkAuthenticator
import br.com.viewsystem_sample.structural.network.connectivity.NetworkConnectivityProvider
import br.com.viewsystem_sample.structural.network.connectivity.NetworkConnectivityProviderImpl
import br.com.viewsystem_sample.structural.session.AppSessionProvider
import br.com.viewsystem_sample.structural.network.interceptor.NetworkInterceptor
import okhttp3.Authenticator
import okhttp3.Interceptor

fun createNetworkConnectivityProvider(context: Context): NetworkConnectivityProvider =
    NetworkConnectivityProviderImpl(context)

fun createNetworkAuthenticator(
    appSessionProvider: AppSessionProvider,
    loginRepository: LoginRepository,
    preferencesDataSource: PreferencesDataSource
): Authenticator = NetworkAuthenticator(appSessionProvider, loginRepository, preferencesDataSource)

fun createNetworkInterceptor(preferencesDataSource: PreferencesDataSource): Interceptor =
    NetworkInterceptor(preferencesDataSource)
