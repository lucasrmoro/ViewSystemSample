package br.com.viewsystem_sample.featureA.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.viewsystem_sample.featureA.useCase.GetSomethingUseCase
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.ONE
import br.com.viewsystem_sample.structural.core.ext.ONE_HUNDRED
import br.com.viewsystem_sample.structural.core.ext.onError
import br.com.viewsystem_sample.structural.core.ext.onSuccess
import br.com.viewsystem_sample.structural.designsystem.adapter.model.Foo
import br.com.viewsystem_sample.structural.designsystem.components.recyclerView.SampleEndlessRecyclerViewCallbacks
import kotlinx.coroutines.async

class ListViewModel(
    private val getSomethingUseCase: GetSomethingUseCase
) : BaseViewModel(), SampleEndlessRecyclerViewCallbacks {

    private val _onLoadItems = MutableLiveData<Pair<List<Foo>?, String?>>()
    val onLoadItems: LiveData<Pair<List<Foo>?, String?>> = _onLoadItems

    private var _pagesCount: Int = Int.ONE
    override val pagesCount: Int
        get() = _pagesCount

    private val itemsPerPage = Int.ONE_HUNDRED

    override fun loadItems(page: Int) {
        launch {
//            getSomethingUseCase().onError {
//                _onLoadItems.postValue(null to message)
//            }.onSuccess {
                _onLoadItems.postValue(
                    (1..100).map {
                        Foo(it.toLong(), "Teste $it")
                    } to null
                )

        }
    }

    fun loadItems() {
        launch {
            async { _pagesCount = 1 }.await()
            loadItems(Int.ONE)
        }
    }
}