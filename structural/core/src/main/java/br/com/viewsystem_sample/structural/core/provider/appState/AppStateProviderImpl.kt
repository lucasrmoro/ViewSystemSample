package br.com.viewsystem_sample.structural.core.provider.appState

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.viewsystem_sample.structural.core.provider.appSession.AppSessionProvider
import br.com.viewsystem_sample.structural.core.provider.network.provider.NetworkConnectivityProvider

internal class AppStateProviderImpl(
    private val appSessionProvider: AppSessionProvider,
    private val networkConnectivityProvider: NetworkConnectivityProvider
) : AppStateProvider {

    private val _state = MediatorLiveData<AppState>()

    override val state: LiveData<AppState> = _state

    init {
        _state.addSource(appSessionProvider.isSessionExpired) { updateState() }
        _state.addSource(networkConnectivityProvider.isInternetAvailable) { updateState() }
    }

    private fun updateState() {
        val internetAvailable = networkConnectivityProvider.isInternetAvailable.value ?: return
        val sessionExpired = appSessionProvider.isSessionExpired.value ?: false
        val updatedState = AppState(
            session = if (sessionExpired) SessionStatus.EXPIRED else SessionStatus.ACTIVE,
            connectivity = if (internetAvailable) ConnectivityStatus.ONLINE else ConnectivityStatus.OFFLINE
        )

        if (_state.value != updatedState) {
            _state.value = updatedState
        }
    }

}
