package br.com.viewsystem_sample.featureA.service

import br.com.viewsystem_sample.featureA.dto.SomeDTO

interface FeatureAService {

    suspend fun getSomething(someParam: String): List<SomeDTO>

}
