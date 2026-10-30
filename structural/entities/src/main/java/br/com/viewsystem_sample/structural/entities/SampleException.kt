package br.com.viewsystem_sample.structural.entities

class SampleException(
    override val message: String?,
    val apiException: ApiException = ApiException()
) : Throwable()