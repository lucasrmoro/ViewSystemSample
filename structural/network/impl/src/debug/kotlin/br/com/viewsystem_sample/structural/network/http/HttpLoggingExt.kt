package br.com.viewsystem_sample.structural.network.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal fun OkHttpClient.Builder.withHttpLogging(): OkHttpClient.Builder =
    addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
