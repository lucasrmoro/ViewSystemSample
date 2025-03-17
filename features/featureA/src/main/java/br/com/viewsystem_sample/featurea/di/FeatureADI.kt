package br.com.viewsystem_sample.featurea.di

import br.com.viewsystem_sample.featurea.presentation.viewModel.FeatureAViewModel
import br.com.viewsystem_sample.featurea.presentation.viewModel.FormViewModel
import br.com.viewsystem_sample.featurea.presentation.viewModel.ListViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { FeatureAViewModel() }
    viewModel { ListViewModel(fooRepository = get()) }
    viewModel { FormViewModel(fooRepository = get()) }
}

object FeatureAModule {
    fun init() {
        loadKoinModules(viewModelModule)
    }
}