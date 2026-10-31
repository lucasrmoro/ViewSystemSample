package br.com.viewsystem_sample.featureA

import br.com.viewsystem_sample.featureA.analytics.FeatureAAnalytics
import br.com.viewsystem_sample.featureA.analytics.createFeatureAAnalytics

import br.com.viewsystem_sample.featureA.dataSource.FeatureALocalDataSource
import br.com.viewsystem_sample.featureA.dataSource.FeatureALocalDataSourceImpl
import br.com.viewsystem_sample.featureA.dataSource.FeatureARemoteDataSource
import br.com.viewsystem_sample.featureA.dataSource.FeatureARemoteDataSourceImpl
import br.com.viewsystem_sample.featureA.mapper.SomeMapper
import br.com.viewsystem_sample.featureA.mapper.SomeMapperImpl
import br.com.viewsystem_sample.featureA.navigation.FeatureAEntryPoint
import br.com.viewsystem_sample.featureA.navigation.createFeatureAEntryPoint
import br.com.viewsystem_sample.featureA.repository.FeatureARepository
import br.com.viewsystem_sample.featureA.repository.FeatureARepositoryImpl
import br.com.viewsystem_sample.featureA.service.FeatureAService
import br.com.viewsystem_sample.featureA.useCase.GetSomethingUseCase
import br.com.viewsystem_sample.featureA.useCase.GetSomethingUseCaseImpl
import br.com.viewsystem_sample.featureA.viewModel.FeatureAViewModel
import br.com.viewsystem_sample.featureA.viewModel.FormViewModel
import br.com.viewsystem_sample.featureA.viewModel.ListViewModel
import br.com.viewsystem_sample.structural.core.ext.dispatcherIO
import br.com.viewsystem_sample.structural.network.ext.createService
import br.com.viewsystem_sample.structural.network.ext.retrofit
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

object FeatureAModule {

    private val dataModule = module {
        single<FeatureAAnalytics> { createFeatureAAnalytics(provider = get()) }
        // Remote
        single<FeatureAService> { retrofit.createService() }
        single<FeatureARemoteDataSource> { FeatureARemoteDataSourceImpl(featureAService = get()) }

        // Local
        single<FeatureALocalDataSource> {
            FeatureALocalDataSourceImpl(someDao = get(), someMapper = get())
        }

        // Mapper
        factory<SomeMapper> { SomeMapperImpl() }

        // Repository
        single<FeatureARepository> {
            FeatureARepositoryImpl(
                dispatcher = dispatcherIO,
                featureARemoteDataSource = get(),
                featureALocalDataSource = get()
            )
        }
    }

    private val domainModule = module {
        factory<GetSomethingUseCase> { GetSomethingUseCaseImpl(featureARepository = get()) }
    }

    private val presentationModule = module {
        single<FeatureAEntryPoint> { createFeatureAEntryPoint() }
        viewModelOf(::FeatureAViewModel)
        viewModelOf(::FormViewModel)
        viewModelOf(::ListViewModel)
    }

    fun init() {
        loadKoinModules(listOf(dataModule, domainModule, presentationModule))
    }

}