package br.com.viewsystem_sample.login.service

import br.com.viewsystem_sample.login.model.LoginDTO

interface LoginService {

    suspend fun refreshToken(refreshToken: String): LoginDTO

}
