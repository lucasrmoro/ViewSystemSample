package br.com.viewsystem_sample.core.provider.appSession

import androidx.lifecycle.LiveData

interface AppSessionProvider {

    val isSessionExpired: LiveData<Boolean>

    fun expireSession()

}