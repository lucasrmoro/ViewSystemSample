package br.com.viewsystem_sample.structural.core.provider.appState

import androidx.lifecycle.LiveData

interface AppStateProvider {

    val state: LiveData<AppState>

}
