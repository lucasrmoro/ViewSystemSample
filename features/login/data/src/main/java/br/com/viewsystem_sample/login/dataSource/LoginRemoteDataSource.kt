package br.com.viewsystem_sample.login.dataSource

import br.com.viewsystem_sample.login.model.LoginDTO

interface LoginRemoteDataSource {

    suspend fun refreshToken(refreshToken: String): LoginDTO

}
