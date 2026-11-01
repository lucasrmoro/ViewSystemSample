package br.com.viewsystem_sample.structural.network.ext

import br.com.viewsystem_sample.structural.network.di.RetrofitAuth
import br.com.viewsystem_sample.structural.network.di.RetrofitNoAuth
import br.com.viewsystem_sample.structural.di.DependencyResolver
import br.com.viewsystem_sample.structural.di.get
import retrofit2.Retrofit

val DependencyResolver.retrofit: Retrofit
    get() = get(RetrofitAuth)

val DependencyResolver.retrofitNoAuth: Retrofit
    get() = get(RetrofitNoAuth)
