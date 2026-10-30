package br.com.viewsystem_sample.structural.designsystem.components.recyclerView

interface SampleEndlessRecyclerViewCallbacks {

    val pagesCount: Int?
    fun loadItems(page: Int)

}