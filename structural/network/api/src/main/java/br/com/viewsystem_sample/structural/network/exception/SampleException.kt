package br.com.viewsystem_sample.structural.network.exception

class SampleException(
    override val message: String?,
    val apiException: ApiException = ApiException()
) : Throwable()