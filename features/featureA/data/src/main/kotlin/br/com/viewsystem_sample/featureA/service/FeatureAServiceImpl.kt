package br.com.viewsystem_sample.featureA.service

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import br.com.viewsystem_sample.structural.network.http.HttpClient
import br.com.viewsystem_sample.structural.network.http.HttpRequest
import br.com.viewsystem_sample.structural.network.http.get

class FeatureAServiceImpl(
    private val httpClient: HttpClient
) : FeatureAService {

    override suspend fun getSomething(someParam: String): List<SomeDTO> =
        httpClient.get(
            HttpRequest(
                path = "some_api/{some_param}",
                pathParameters = mapOf("some_param" to someParam)
            )
        )

}
