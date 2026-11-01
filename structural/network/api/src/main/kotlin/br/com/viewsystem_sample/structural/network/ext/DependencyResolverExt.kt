package br.com.viewsystem_sample.structural.network.ext

import br.com.viewsystem_sample.structural.di.DependencyResolver
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.network.di.AuthenticatedHttpClient
import br.com.viewsystem_sample.structural.network.di.UnauthenticatedHttpClient
import br.com.viewsystem_sample.structural.network.http.HttpClient

val DependencyResolver.httpClient: HttpClient
    get() = get(AuthenticatedHttpClient)

val DependencyResolver.httpClientNoAuth: HttpClient
    get() = get(UnauthenticatedHttpClient)
