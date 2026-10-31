package br.com.viewsystem_sample.login

import br.com.viewsystem_sample.login.dataSource.LoginRemoteDataSource
import br.com.viewsystem_sample.login.dataSource.LoginRemoteDataSourceImpl
import br.com.viewsystem_sample.login.repository.LoginRepository
import br.com.viewsystem_sample.login.repository.LoginRepositoryImpl
import br.com.viewsystem_sample.login.service.LoginService
import br.com.viewsystem_sample.structural.network.ext.createService
import br.com.viewsystem_sample.structural.network.ext.retrofitNoAuth
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object LoginModule {

    private val dataModule = module {
        factory<LoginService> { retrofitNoAuth.createService() }
        factory<LoginRemoteDataSource> { LoginRemoteDataSourceImpl(loginService = get()) }
        factory<LoginRepository> { LoginRepositoryImpl(loginRemoteDataSource = get()) }
    }

    fun init() {
        loadKoinModules(dataModule)
    }

}
