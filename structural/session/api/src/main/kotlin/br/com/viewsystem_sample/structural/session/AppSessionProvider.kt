package br.com.viewsystem_sample.structural.session

import androidx.lifecycle.LiveData

interface AppSessionProvider {

    val isSessionExpired: LiveData<Boolean>

    fun expireSession()

}