package br.com.viewsystem_sample.structural.core.provider.appState

import br.com.viewsystem_sample.structural.network.connectivity.ConnectivityStatus
import br.com.viewsystem_sample.structural.session.SessionStatus

data class AppState(
    val session: SessionStatus,
    val connectivity: ConnectivityStatus
)
