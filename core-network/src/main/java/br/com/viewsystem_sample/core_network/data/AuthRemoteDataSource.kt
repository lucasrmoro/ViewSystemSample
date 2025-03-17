package br.com.viewsystem_sample.core_network.data

import br.com.viewsystem_sample.entities.remote.LoginDTO
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {

    suspend fun refreshToken(refreshToken: String): Flow<LoginDTO>

}