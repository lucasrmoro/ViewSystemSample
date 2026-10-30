package br.com.viewsystem_sample.structural.core.provider.appSession

import androidx.lifecycle.LiveData

interface AppSessionProvider {

    val isSessionExpired: LiveData<Boolean>

    fun expireSession()

}