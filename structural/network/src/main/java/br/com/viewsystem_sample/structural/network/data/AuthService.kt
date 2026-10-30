package br.com.viewsystem_sample.structural.network.data

import br.com.viewsystem_sample.structural.entities.remote.LoginDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface AuthService {

    @GET(REFRESH_TOKEN)
    suspend fun refreshToken(@Path(REFRESH_TOKEN_PARAM) refreshToken: String): LoginDTO

    companion object {
        // Params
        private const val REFRESH_TOKEN_PARAM = "refreshToken"

        // Endpoints
        private const val REFRESH_TOKEN = "auth/refresh-token/{$REFRESH_TOKEN_PARAM}"
    }
}