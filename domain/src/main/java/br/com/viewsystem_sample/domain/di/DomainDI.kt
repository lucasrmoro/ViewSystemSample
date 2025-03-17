package br.com.viewsystem_sample.domain.di

import br.com.viewsystem_sample.core.ext.dispatcherIO
import br.com.viewsystem_sample.domain.repository.foo.FooMapper
import br.com.viewsystem_sample.domain.repository.foo.FooMapperImpl
import br.com.viewsystem_sample.domain.repository.foo.FooRepository
import br.com.viewsystem_sample.domain.repository.foo.FooRepositoryImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val fooModule = module {
    factory<FooMapper> { FooMapperImpl() }
    factory<FooRepository> {
        FooRepositoryImpl(
            dispatcher = dispatcherIO,
            fooLocalDataSource = get(),
            fooMapper = get()
        )
    }
}

object DomainModule {
    fun init() {
        loadKoinModules(fooModule)
    }
}