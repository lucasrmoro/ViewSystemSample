package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.di.DependencyQualifier

internal object OkHttpClientAuth: DependencyQualifier {

    override val value: String = "OkHttpClientAuth"

}

internal object OkHttpClientNoAuth: DependencyQualifier {

    override val value: String = "OkHttpClientNoAuth"

}
