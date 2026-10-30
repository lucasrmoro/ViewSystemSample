package br.com.viewsystem_sample.login.dataSource

import br.com.viewsystem_sample.login.service.LoginService

class LoginRemoteDataSourceImpl(
    private val loginService: LoginService
) : LoginRemoteDataSource {

    override suspend fun refreshToken(refreshToken: String) = loginService.refreshToken(refreshToken)

}
