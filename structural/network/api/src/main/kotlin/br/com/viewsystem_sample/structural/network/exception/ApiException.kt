package br.com.viewsystem_sample.structural.network.exception

import br.com.viewsystem_sample.structural.network.http.JsonName

data class ApiException(
    @JsonName("Status") val status: Int? = null,
    @JsonName("Message") val message: String? = null,
    @JsonName("ExceptionCode") val exceptionCode: Int? = null
)
