package br.com.viewsystem_sample.core_ui.components.recyclerView

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.viewsystem_sample.core.ext.ONE
import br.com.viewsystem_sample.core_ui.adapter.SampleGenericAdapter
import br.com.viewsystem_sample.core_ui.adapter.model.Loading
import br.com.viewsystem_sample.core_ui.adapter.model.base.AdapterItem
import br.com.viewsystem_sample.core_ui.components.recyclerView.SampleEndlessRecyclerViewScrollListener.SampleEndlessRecyclerViewScrollListenerCallbacks

class SampleEndlessRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RecyclerView(context, attrs), SampleEndlessRecyclerViewScrollListenerCallbacks {

    private var callbacks: SampleEndlessRecyclerViewCallbacks? = null

    private val sampleGenericAdapter: SampleGenericAdapter?
        get() = adapter as? SampleGenericAdapter

    private val items: List<AdapterItem>
        get() = sampleGenericAdapter?.currentList.orEmpty()

    var currentPage = Int.ONE
        private set

    override val hasItemsToLoad: Boolean
        get() = callbacks?.pagesCount?.let { currentPage <= it } ?: false

    private var _isLoading: Boolean = false
    override val isLoading: Boolean
        get() = _isLoading

    override fun loadItems() {
        showLoading {
            callbacks?.loadItems(currentPage)
        }
    }

    fun setup(adapter: SampleGenericAdapter, callbacks: SampleEndlessRecyclerViewCallbacks) {
        this.adapter = adapter
        this.callbacks = callbacks
        layoutManager = LinearLayoutManager(context)
        addOnScrollListener(SampleEndlessRecyclerViewScrollListener(this))
    }

    fun <T : AdapterItem> onLoadItemsSuccess(newItems: List<T>) {
        removeLoading {
            sampleGenericAdapter?.submitList(
                if (currentPage == Int.ONE) newItems else items.plus(newItems)
            )
            currentPage++
        }
    }

    fun onLoadItemsError() {
        removeLoading()
    }

    fun resetCurrentPage() {
        currentPage = Int.ONE
    }

    private fun showLoading(onLoadingShown: () -> Unit) {
        post {
            _isLoading = true
            sampleGenericAdapter?.submitList(items.plus(Loading())) {
                onLoadingShown()
            }
        }
    }

    private fun removeLoading(onLoadingRemoved: () -> Unit = {}) {
        post {
            _isLoading = false
            sampleGenericAdapter?.submitList(items.filter { it !is Loading }) {
                onLoadingRemoved()
            }
        }
    }
}