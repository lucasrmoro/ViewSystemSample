package br.com.viewsystem_sample.structural.core.provider.appState

import br.com.viewsystem_sample.structural.network.connectivity.NetworkConnectivityProvider
import br.com.viewsystem_sample.structural.session.AppSessionProvider

fun createAppStateProvider(
    appSessionProvider: AppSessionProvider,
    networkConnectivityProvider: NetworkConnectivityProvider
): AppStateProvider = AppStateProviderImpl(appSessionProvider, networkConnectivityProvider)
