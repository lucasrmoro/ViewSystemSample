package br.com.viewsystem_sample.structural.network.http

import okhttp3.OkHttpClient

internal fun OkHttpClient.Builder.withHttpLogging(): OkHttpClient.Builder = this
