package br.com.viewsystem_sample.login.repository

import br.com.viewsystem_sample.login.dataSource.LoginRemoteDataSource

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {

    override suspend fun refreshToken(refreshToken: String): String? =
        loginRemoteDataSource.refreshToken(refreshToken).accessToken

}
