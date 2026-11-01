package br.com.viewsystem_sample.structural.network.di

import br.com.viewsystem_sample.structural.di.DependencyQualifier

object RetrofitNoAuth : DependencyQualifier {

    override val value: String = "RetrofitNoAuth"

}

object RetrofitAuth : DependencyQualifier {

    override val value: String = "Retrofit"

}
