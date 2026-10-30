package br.com.viewsystem_sample.structural.core.provider.appState

data class AppState(
    val session: SessionStatus,
    val connectivity: ConnectivityStatus
)
