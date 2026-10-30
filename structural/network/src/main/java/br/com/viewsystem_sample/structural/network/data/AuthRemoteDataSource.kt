package br.com.viewsystem_sample.structural.network.data

import br.com.viewsystem_sample.structural.entities.remote.LoginDTO
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {

    suspend fun refreshToken(refreshToken: String): Flow<LoginDTO>

}