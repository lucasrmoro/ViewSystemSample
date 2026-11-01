package br.com.viewsystem_sample.login.service

import br.com.viewsystem_sample.login.model.LoginDTO
import br.com.viewsystem_sample.structural.network.http.HttpClient
import br.com.viewsystem_sample.structural.network.http.HttpRequest
import br.com.viewsystem_sample.structural.network.http.get

class LoginServiceImpl(
    private val httpClient: HttpClient
) : LoginService {

    override suspend fun refreshToken(refreshToken: String): LoginDTO =
        httpClient.get(HttpRequest("auth/refresh-token/{refreshToken}", pathParameters = mapOf("refreshToken" to refreshToken)))

}
