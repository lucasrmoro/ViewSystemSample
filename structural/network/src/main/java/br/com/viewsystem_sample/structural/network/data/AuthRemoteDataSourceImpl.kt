package br.com.viewsystem_sample.structural.network.data

import br.com.viewsystem_sample.structural.core.base.dataSource.BaseDataSource
import br.com.viewsystem_sample.structural.entities.remote.LoginDTO
import kotlinx.coroutines.flow.Flow

internal class AuthRemoteDataSourceImpl(
    private val authService: AuthService
) : AuthRemoteDataSource, BaseDataSource() {

    override suspend fun refreshToken(refreshToken: String): Flow<LoginDTO> = call {
        authService.refreshToken(refreshToken)
    }

}