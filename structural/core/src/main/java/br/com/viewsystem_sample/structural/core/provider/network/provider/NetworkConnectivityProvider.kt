package br.com.viewsystem_sample.structural.core.provider.network.provider

import androidx.lifecycle.LiveData

interface NetworkConnectivityProvider {

    val isInternetAvailable: LiveData<Boolean>

}