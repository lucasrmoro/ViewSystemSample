package br.com.viewsystem_sample.featureA.repository

import br.com.viewsystem_sample.featureA.dataSource.FeatureALocalDataSource
import br.com.viewsystem_sample.featureA.dataSource.FeatureARemoteDataSource
import br.com.viewsystem_sample.featureA.model.SomeModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class FeatureARepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val featureARemoteDataSource: FeatureARemoteDataSource,
    private val featureALocalDataSource: FeatureALocalDataSource,
) : FeatureARepository {

    override suspend fun insert(someModel: SomeModel): Long = 1

    override suspend fun update(someModel: SomeModel) {
        // TODO("Not yet implemented")
    }

    override suspend fun delete(someModel: SomeModel) {
        // TODO("Not yet implemented")
    }

    override suspend fun deleteBy(id: Long) {
        // TODO("Not yet implemented")
    }

    override suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<SomeModel>> = emptyFlow()

    override suspend fun getBy(id: Long): SomeModel? = null

    override suspend fun getPagesCount(itemsPerPage: Int): Int = 1

}