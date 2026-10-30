package br.com.viewsystem_sample.featureA.dataSource

import br.com.viewsystem_sample.featureA.dto.SomeDTO
import br.com.viewsystem_sample.featureA.mapper.SomeMapper
import br.com.viewsystem_sample.structural.core.base.dataSource.BaseDataSource
import br.com.viewsystem_sample.structural.core.ext.ONE_HUNDRED
import br.com.viewsystem_sample.structural.core.ext.ZERO
import br.com.viewsystem_sample.structural.local_storage.db.dao.SomeDao
import kotlinx.coroutines.flow.Flow

class FeatureALocalDataSourceImpl(
    private val someDao: SomeDao,
    private val someMapper: SomeMapper
) : FeatureALocalDataSource, BaseDataSource() {

    override suspend fun getSomething(): Flow<List<SomeDTO>> = call {
        someMapper.toSomeDTOList(someDao.getAll(Int.ZERO, Int.ONE_HUNDRED))
    }

}