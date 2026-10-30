package br.com.viewsystem_sample.app

import android.app.Application
import br.com.viewsystem_sample.di.AppDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
            AppDI.load()
        }
    }

}