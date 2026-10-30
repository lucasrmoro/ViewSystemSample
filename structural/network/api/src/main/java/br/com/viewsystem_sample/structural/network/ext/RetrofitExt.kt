package br.com.viewsystem_sample.structural.network.ext

import retrofit2.Retrofit

inline fun <reified T> Retrofit.createService(): T = create(T::class.java)