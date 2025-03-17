package br.com.viewsystem_sample.featurea.presentation.fragment

import br.com.viewsystem_sample.core_ui.adapter.SampleGenericAdapter
import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.core_ui.base.fragment.BaseFragment
import br.com.viewsystem_sample.core_ui.ext.showToast
import br.com.viewsystem_sample.featurea.databinding.FragListBinding
import br.com.viewsystem_sample.featurea.presentation.viewModel.ListViewModel

class ListFragment private constructor() :
    BaseFragment<FragListBinding, ListViewModel>(FragListBinding::inflate) {

    private val listAdapter = SampleGenericAdapter()

    override fun setupViews() {
        setupListeners()
        setupObservers()
    }

    override fun onFragmentVisible() {
        showScreenLoading()
        binding.recyclerView.resetCurrentPage()
        viewModel.loadItems()
    }

    override fun setupListeners() = with(binding) {
        btnAdd.setOnClickListener {
            navigate(FormFragment.newInstance())
        }
        recyclerView.setup(adapter = listAdapter, callbacks = viewModel)
    }

    override fun setupObservers() = with(viewModel) {
        onLoadItems.observe(viewLifecycleOwner, ::onLoadItems)
    }

    private fun onLoadItems(result: Pair<List<Foo>?, String?>) = with(binding) {
        dismissScreenLoading()
        result.first?.let {
            recyclerView.onLoadItemsSuccess(it)
        } ?: run {
            recyclerView.onLoadItemsError()
            showToast(result.second ?: defaultErrorMessage)
        }
    }

    companion object {
        fun newInstance() = ListFragment()
    }
}