package br.com.viewsystem_sample.login

import br.com.viewsystem_sample.login.repository.LoginRepository
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.network.di.RetrofitNoAuth
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import retrofit2.HttpException
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class LoginRepositoryTest {

    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start()
        startKoin {}
        CoreNetworkModule.init()
        LoginModule.init()

        val configuredRetrofit = GlobalContext.get().get<Retrofit>(RetrofitNoAuth)
        assertSame(Authenticator.NONE, (configuredRetrofit.callFactory() as OkHttpClient).authenticator)
        loadKoinModules(module {
            single<Retrofit>(RetrofitNoAuth) {
                configuredRetrofit.newBuilder().baseUrl(server.url("/")).build()
            }
        })
    }

    @After
    fun tearDown() {
        stopKoin()
        server.shutdown()
    }

    @Test
    fun refreshTokenUsesLoginServiceWithoutAuthorization() = runBlocking {
        server.enqueue(MockResponse().setBody(
            """{"AccessToken":"new-access-token","RefreshToken":"new-refresh-token"}"""
        ))

        val accessToken = GlobalContext.get().get<LoginRepository>().refreshToken("refresh-token")

        assertEquals("new-access-token", accessToken)
        val request = server.takeRequest(1, TimeUnit.SECONDS)
        assertEquals("/auth/refresh-token/refresh-token", request?.path)
        assertNull(request?.getHeader("Authorization"))
        assertEquals(1, server.requestCount)
    }

    @Test
    fun rejectedRefreshDoesNotRetryAuthentication() = runBlocking {
        server.enqueue(MockResponse().setResponseCode(401))

        val error = runCatching {
            GlobalContext.get().get<LoginRepository>().refreshToken("expired-refresh-token")
        }.exceptionOrNull()

        assertEquals(401, (error as? HttpException)?.code())
        assertEquals(1, server.requestCount)
    }

}
