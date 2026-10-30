package br.com.viewsystem_sample.structural.network.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object RetrofitNoAuth : Qualifier {

    override val value: QualifierValue = "RetrofitNoAuth"

}

object RetrofitAuth : Qualifier {

    override val value: QualifierValue = "Retrofit"

}
