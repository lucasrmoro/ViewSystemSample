package br.com.viewsystem_sample.featureA.dataSource

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import kotlinx.coroutines.flow.Flow

interface FeatureALocalDataSource {

    suspend fun getSomething(): Flow<List<SomeDTO>>

}