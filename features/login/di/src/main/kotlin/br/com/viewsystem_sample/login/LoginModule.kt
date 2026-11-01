package br.com.viewsystem_sample.login

import br.com.viewsystem_sample.login.analytics.LoginAnalytics
import br.com.viewsystem_sample.login.analytics.createLoginAnalytics
import br.com.viewsystem_sample.login.dataSource.LoginRemoteDataSource
import br.com.viewsystem_sample.login.dataSource.LoginRemoteDataSourceImpl
import br.com.viewsystem_sample.login.repository.LoginRepository
import br.com.viewsystem_sample.login.repository.LoginRepositoryImpl
import br.com.viewsystem_sample.login.service.LoginService
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.network.ext.createService
import br.com.viewsystem_sample.structural.network.ext.retrofitNoAuth

object LoginModule {

    private val dataModule = dependencyModule {
        singleton<LoginAnalytics> { createLoginAnalytics(provider = get()) }
        factory<LoginService> { retrofitNoAuth.createService() }
        factory<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(loginService = get()) }
        factory<LoginRepository> { LoginRepositoryImpl(loginRemoteDataSource = get(), analytics = get()) }
    }

    fun init() {
        loadModules(dataModule)
    }

}
