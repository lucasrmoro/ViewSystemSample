package br.com.viewsystem_sample.data.foo.local

import br.com.viewsystem_sample.entities.db.FooDbEntity
import kotlinx.coroutines.flow.Flow

interface FooLocalDataSource {

    suspend fun insert(foo: FooDbEntity): Long
    suspend fun update(foo: FooDbEntity)
    suspend fun delete(foo: FooDbEntity)
    suspend fun deleteBy(id: Long)
    suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<FooDbEntity>>
    suspend fun getBy(id: Long): FooDbEntity?
    suspend fun getPagesCount(itemsPerPage: Int): Int

}