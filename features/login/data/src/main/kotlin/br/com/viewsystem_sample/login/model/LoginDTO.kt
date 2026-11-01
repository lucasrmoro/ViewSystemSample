package br.com.viewsystem_sample.login.model

import br.com.viewsystem_sample.structural.network.http.JsonName

data class LoginDTO(
    @JsonName("AccessToken") val accessToken: String?,
    @JsonName("RefreshToken") val refreshToken: String?
)