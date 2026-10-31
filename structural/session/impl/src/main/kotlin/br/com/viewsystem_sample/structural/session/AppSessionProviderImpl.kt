package br.com.viewsystem_sample.structural.session

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

internal class AppSessionProviderImpl : AppSessionProvider {

    private val _isSessionExpired = MutableLiveData(false)
    override val isSessionExpired: LiveData<Boolean> = _isSessionExpired

    override fun expireSession() {
        _isSessionExpired.postValue(true)
    }

}