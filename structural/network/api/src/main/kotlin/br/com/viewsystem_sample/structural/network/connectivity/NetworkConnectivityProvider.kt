package br.com.viewsystem_sample.structural.network.connectivity

import androidx.lifecycle.LiveData

interface NetworkConnectivityProvider {

    val isInternetAvailable: LiveData<Boolean>

}