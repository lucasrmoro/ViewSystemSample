package br.com.viewsystem_sample.featureA.analytics

import br.com.viewsystem_sample.structural.analytics.AnalyticsResult

interface FeatureAAnalytics {

    fun listLoadStarted(page: Int): AnalyticsResult

    fun listLoadFinished(page: Int, itemCount: Int): AnalyticsResult

}
