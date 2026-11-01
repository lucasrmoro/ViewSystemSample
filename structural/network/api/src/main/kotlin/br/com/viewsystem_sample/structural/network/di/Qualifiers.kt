package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.di.DependencyQualifier

object AuthenticatedHttpClient : DependencyQualifier {

    override val value: String = "AuthenticatedHttpClient"

}

object UnauthenticatedHttpClient : DependencyQualifier {

    override val value: String = "UnauthenticatedHttpClient"

}
