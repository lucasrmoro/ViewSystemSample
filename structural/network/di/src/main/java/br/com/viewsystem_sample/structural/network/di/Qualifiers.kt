package br.com.viewsystem_sample.structural.network.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

internal object OkHttpClientAuth: Qualifier {

    override val value: QualifierValue = "OkHttpClientAuth"

}

internal object OkHttpClientNoAuth: Qualifier {

    override val value: QualifierValue = "OkHttpClientNoAuth"

}