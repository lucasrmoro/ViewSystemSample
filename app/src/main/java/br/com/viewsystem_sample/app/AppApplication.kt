package br.com.viewsystem_sample.app

import android.app.Application
import br.com.viewsystem_sample.BuildConfig
import br.com.viewsystem_sample.di.AppDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setupKoin()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    private fun setupKoin() {
        startKoin {
            androidContext(applicationContext)
        }
        AppDI.load()
    }

}