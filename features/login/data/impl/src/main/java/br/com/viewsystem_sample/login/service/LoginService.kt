package br.com.viewsystem_sample.login.service

import br.com.viewsystem_sample.login.model.LoginDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginService {

    @GET(REFRESH_TOKEN)
    suspend fun refreshToken(@Path(REFRESH_TOKEN_PARAM) refreshToken: String): LoginDTO

    companion object {
        private const val REFRESH_TOKEN_PARAM = "refreshToken"

        private const val REFRESH_TOKEN = "auth/refresh-token/{$REFRESH_TOKEN_PARAM}"
    }

}