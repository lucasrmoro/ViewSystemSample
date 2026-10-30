package br.com.viewsystem_sample.featureA.viewModel

import br.com.viewsystem_sample.featureA.useCase.GetSomethingUseCase
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.onError
import br.com.viewsystem_sample.structural.core.ext.onSuccess

class FormViewModel(private val getSomethingUseCase: GetSomethingUseCase) : BaseViewModel() {

    fun doSomething() {
        launch {
            getSomethingUseCase().onError {

            }.onSuccess {

            }
        }
    }

}