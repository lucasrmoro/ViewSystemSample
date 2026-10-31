package br.com.viewsystem_sample.structural.designsystem.components.recyclerView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.viewsystem_sample.structural.core.ext.THREE
import br.com.viewsystem_sample.structural.core.ext.ZERO
import br.com.viewsystem_sample.structural.core.ext.orZero
import br.com.viewsystem_sample.structural.logger.Logger

internal class SampleEndlessRecyclerViewScrollListener(
    private val callbacks: SampleEndlessRecyclerViewScrollListenerCallbacks,
    private val logger: Logger
) : RecyclerView.OnScrollListener() {

    private val threshold = Int.THREE

    override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(rv, dx, dy)

        with(callbacks) {
            logger.debug("isLoading: $isLoading")
            logger.debug("hasItemsToLoad: $hasItemsToLoad")
            logger.debug("rv.isLastItemVisible: ${rv.isLastItemVisible()}")
            if (isLoading.not() && hasItemsToLoad && rv.isLastItemVisible() && dy >= Int.ZERO) {
                loadItems()
            }
        }
    }

    private fun RecyclerView.isLastItemVisible(): Boolean {
        val layoutManager = (this.layoutManager as? LinearLayoutManager)
        val visibleItemPositionToLoadMoreItems = adapter?.itemCount?.minus(threshold).orZero()

        return layoutManager?.findLastCompletelyVisibleItemPosition()
            .orZero() >= visibleItemPositionToLoadMoreItems
    }

    internal interface SampleEndlessRecyclerViewScrollListenerCallbacks {

        val hasItemsToLoad: Boolean
        val isLoading: Boolean

        fun loadItems()

    }
}