package br.com.viewsystem_sample.domain.repository.foo

import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.domain.ext.execute
import br.com.viewsystem_sample.data.foo.local.FooLocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class FooRepositoryImpl(
    private val dispatcher: CoroutineDispatcher,
    private val fooLocalDataSource: br.com.viewsystem_sample.data.foo.local.FooLocalDataSource,
    private val fooMapper: FooMapper
) : FooRepository {

    override suspend fun insert(foo: Foo): Long = dispatcher.execute {
        fooLocalDataSource.insert(fooMapper.toDTO(foo))
    }

    override suspend fun update(foo: Foo) =
        dispatcher.execute { fooLocalDataSource.update(fooMapper.toDTO(foo)) }

    override suspend fun delete(foo: Foo) =
        dispatcher.execute { fooLocalDataSource.delete(fooMapper.toDTO(foo)) }

    override suspend fun deleteBy(id: Long) = dispatcher.execute { fooLocalDataSource.deleteBy(id) }

    override suspend fun getAll(page: Int, itemsPerPage: Int): Flow<List<Foo>> =
        fooMapper.toDomainModelList(
            fooLocalDataSource.getAll(page = page, itemsPerPage = itemsPerPage)
        )

    override suspend fun getBy(id: Long): Foo? = dispatcher.execute {
        fooMapper.toDomainModel(fooLocalDataSource.getBy(id))
    }

    override suspend fun getPagesCount(itemsPerPage: Int): Int =
        fooLocalDataSource.getPagesCount(itemsPerPage)

}