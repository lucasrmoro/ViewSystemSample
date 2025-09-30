package br.com.viewsystem_sample.core_network.ext

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun Retrofit.Builder.setConverterFactory(): Retrofit.Builder =
    addConverterFactory(GsonConverterFactory.create())

inline fun <reified T> Retrofit.createService(): T = create(T::class.java)