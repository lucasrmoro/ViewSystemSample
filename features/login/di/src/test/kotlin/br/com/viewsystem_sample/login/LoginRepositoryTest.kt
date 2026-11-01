package br.com.viewsystem_sample.login

import br.com.viewsystem_sample.login.analytics.LoginAnalytics
import br.com.viewsystem_sample.login.repository.LoginRepository
import br.com.viewsystem_sample.structural.analytics.AnalyticsResult
import br.com.viewsystem_sample.structural.di.Dependencies
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.di.startDependencyInjection
import br.com.viewsystem_sample.structural.di.stopDependencyInjection
import br.com.viewsystem_sample.structural.network.di.CoreNetworkModule
import br.com.viewsystem_sample.structural.network.di.RetrofitNoAuth
import java.util.concurrent.TimeUnit
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
import retrofit2.HttpException
import retrofit2.Retrofit

class LoginRepositoryTest {

    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start()
        startDependencyInjection {}
        CoreNetworkModule.init()
        LoginModule.init()

        val configuredRetrofit = Dependencies.get<Retrofit>(RetrofitNoAuth)
        assertSame(Authenticator.NONE, (configuredRetrofit.callFactory() as OkHttpClient).authenticator)
        loadModules(
            dependencyModule {
                singleton<LoginAnalytics> {
                    object : LoginAnalytics {

                        override fun tokenRefreshStarted() = AnalyticsResult.UNAVAILABLE

                        override fun tokenRefreshFinished(succeeded: Boolean) = AnalyticsResult.UNAVAILABLE

                    }
                }
                singleton<Retrofit>(RetrofitNoAuth) {
                    configuredRetrofit.newBuilder().baseUrl(server.url("/")).build()
                }
            }
        )
    }

    @After
    fun tearDown() {
        stopDependencyInjection()
        server.shutdown()
    }

    @Test
    fun refreshTokenUsesLoginServiceWithoutAuthorization() = runBlocking {
        server.enqueue(MockResponse().setBody(
            """{"AccessToken":"new-access-token","RefreshToken":"new-refresh-token"}"""
        ))

        val accessToken = Dependencies.get<LoginRepository>().refreshToken("refresh-token")

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
            Dependencies.get<LoginRepository>().refreshToken("expired-refresh-token")
        }.exceptionOrNull()

        assertEquals(401, (error as? HttpException)?.code())
        assertEquals(1, server.requestCount)
    }

}
