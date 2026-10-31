package br.com.viewsystem_sample.structural.network.ext

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val REQUEST_TIMEOUT_IN_SECONDS = 30L

internal fun OkHttpClient.Builder.setTimeouts() =
    callTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(REQUEST_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)