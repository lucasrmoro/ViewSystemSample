package br.com.viewsystem_sample.featureA.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.viewsystem_sample.featureA.analytics.FeatureAAnalytics
import br.com.viewsystem_sample.featureA.useCase.GetSomethingUseCase
import br.com.viewsystem_sample.structural.core.base.viewModel.BaseViewModel
import br.com.viewsystem_sample.structural.core.ext.ONE
import br.com.viewsystem_sample.structural.core.ext.ONE_HUNDRED
import br.com.viewsystem_sample.featureA.model.SomeModel
import kotlinx.coroutines.async

class ListViewModel(
    private val getSomethingUseCase: GetSomethingUseCase,
    private val analytics: FeatureAAnalytics
) : BaseViewModel() {

    private val _onLoadItems = MutableLiveData<Pair<List<SomeModel>?, String?>>()
    val onLoadItems: LiveData<Pair<List<SomeModel>?, String?>> = _onLoadItems

    private var _pagesCount: Int = Int.ONE
    val pagesCount: Int
        get() = _pagesCount

    private val itemsPerPage = Int.ONE_HUNDRED

    fun loadItems(page: Int) {
        launch {
            analytics.listLoadStarted(page)
//            getSomethingUseCase().onError {
//                _onLoadItems.postValue(null to message)
//            }.onSuccess {
            val items = (1..itemsPerPage).map {
                SomeModel(it.toLong(), "Teste $it")
            }
            _onLoadItems.postValue(items to null)
            analytics.listLoadFinished(page, itemCount = items.size)
        }
    }

    fun loadItems() {
        launch {
            async { _pagesCount = 1 }.await()
            loadItems(Int.ONE)
        }
    }
}