package br.com.viewsystem_sample.login.repository

interface LoginRepository {

    suspend fun refreshToken(refreshToken: String): String?

}
