package br.com.viewsystem_sample.featurea.presentation.viewModel

import br.com.viewsystem_sample.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.domain.repository.foo.FooRepository

class FormViewModel(private val fooRepository: FooRepository) : BaseViewModel() {

    fun insert(message: String) {
        launch {
            fooRepository.insert(Foo(msg = message))
        }
    }

}