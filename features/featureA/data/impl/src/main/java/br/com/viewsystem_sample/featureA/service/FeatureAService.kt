package br.com.viewsystem_sample.featureA.service

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface FeatureAService {

    @GET(SOME_API)
    suspend fun getSomething(@Path(SOME_PARAM) someParam: String): List<SomeDTO>

    companion object {
        // Params
        private const val SOME_PARAM = "some_param"

        // Endpoints
        private const val SOME_API = "some_api/{$SOME_PARAM}"
    }
}