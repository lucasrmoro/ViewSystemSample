package br.com.viewsystem_sample.login.analytics

import br.com.viewsystem_sample.structural.analytics.AnalyticsEvent
import br.com.viewsystem_sample.structural.analytics.AnalyticsProvider
import br.com.viewsystem_sample.structural.analytics.AnalyticsResult

internal class LoginAnalyticsImpl(private val provider: AnalyticsProvider) : LoginAnalytics {

    override fun tokenRefreshStarted(): AnalyticsResult =
        provider.track(AnalyticsEvent("login_token_refresh_started"))

    override fun tokenRefreshFinished(succeeded: Boolean): AnalyticsResult =
        provider.track(AnalyticsEvent(
            "login_token_refresh_finished",
            "succeeded" to succeeded.toString()
        ))

}

fun createLoginAnalytics(provider: AnalyticsProvider): LoginAnalytics = LoginAnalyticsImpl(provider)
