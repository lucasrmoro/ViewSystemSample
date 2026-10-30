package br.com.viewsystem_sample.structural.network.model

import com.google.gson.annotations.SerializedName

data class LoginDTO(
    @SerializedName("AccessToken") val accessToken: String?,
    @SerializedName("RefreshToken") val refreshToken: String?
)