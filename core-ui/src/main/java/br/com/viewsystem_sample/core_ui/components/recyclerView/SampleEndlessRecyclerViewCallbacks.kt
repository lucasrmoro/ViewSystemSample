package br.com.viewsystem_sample.core_ui.components.recyclerView

interface SampleEndlessRecyclerViewCallbacks {

    val pagesCount: Int?
    fun loadItems(page: Int)

}