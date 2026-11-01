package br.com.viewsystem_sample.featureA

import br.com.viewsystem_sample.featureA.analytics.FeatureAAnalytics
import br.com.viewsystem_sample.featureA.analytics.createFeatureAAnalytics
import br.com.viewsystem_sample.featureA.dataSource.FeatureALocalDataSource
import br.com.viewsystem_sample.featureA.dataSource.FeatureALocalDataSourceImpl
import br.com.viewsystem_sample.featureA.dataSource.FeatureARemoteDataSource
import br.com.viewsystem_sample.featureA.dataSource.FeatureARemoteDataSourceImpl
import br.com.viewsystem_sample.featureA.mapper.SomeMapper
import br.com.viewsystem_sample.featureA.mapper.SomeMapperImpl
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
import br.com.viewsystem_sample.structural.di.dependencyModule
import br.com.viewsystem_sample.structural.di.factory
import br.com.viewsystem_sample.structural.di.get
import br.com.viewsystem_sample.structural.di.loadModules
import br.com.viewsystem_sample.structural.di.singleton
import br.com.viewsystem_sample.structural.navigation.core.NavigationRegistry
import br.com.viewsystem_sample.structural.network.ext.createService
import br.com.viewsystem_sample.structural.network.ext.retrofit

object FeatureAModule {

    private val dataModule = dependencyModule {
        singleton<FeatureAAnalytics> { createFeatureAAnalytics(provider = get()) }
        // Remote
        singleton<FeatureAService> { retrofit.createService() }
        singleton<FeatureARemoteDataSource> { FeatureARemoteDataSourceImpl(featureAService = get()) }

        // Local
        singleton<FeatureALocalDataSource> {
            FeatureALocalDataSourceImpl(someDao = get(), someMapper = get())
        }

        // Mapper
        factory<SomeMapper> { SomeMapperImpl() }

        // Repository
        singleton<FeatureARepository> {
            FeatureARepositoryImpl(
                dispatcher = dispatcherIO,
                featureARemoteDataSource = get(),
                featureALocalDataSource = get()
            )
        }
    }

    private val domainModule = dependencyModule {
        factory<GetSomethingUseCase> { GetSomethingUseCaseImpl(featureARepository = get()) }
    }

    private val presentationModule = dependencyModule {
        factory { FeatureAViewModel() }
        factory { FormViewModel(getSomethingUseCase = get()) }
        factory { ListViewModel(getSomethingUseCase = get(), analytics = get()) }
    }

    fun init(navigationRegistry: NavigationRegistry) {
        loadModules(
            dataModule,
            domainModule,
            presentationModule
        )
        navigationRegistry.register(createFeatureAEntryPoint())
    }

}
