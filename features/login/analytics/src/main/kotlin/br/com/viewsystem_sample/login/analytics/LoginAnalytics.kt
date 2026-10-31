package br.com.viewsystem_sample.login.analytics

import br.com.viewsystem_sample.structural.analytics.AnalyticsResult

interface LoginAnalytics {

    fun tokenRefreshStarted(): AnalyticsResult

    fun tokenRefreshFinished(succeeded: Boolean): AnalyticsResult

}
