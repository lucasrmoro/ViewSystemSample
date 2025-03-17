package br.com.viewsystem_sample.core_network.ext

import br.com.viewsystem_sample.core_network.di.RetrofitAuth
import br.com.viewsystem_sample.core_network.di.RetrofitNoAuth
import org.koin.core.scope.Scope
import retrofit2.Retrofit

val Scope.retrofit: Retrofit
    get() = get(RetrofitAuth)

val Scope.retrofitNoAuth: Retrofit
    get() = get(RetrofitNoAuth)