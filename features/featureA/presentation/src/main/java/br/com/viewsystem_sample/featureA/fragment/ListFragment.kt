package br.com.viewsystem_sample.featureA.fragment

import br.com.viewsystem_sample.featureA.viewModel.ListViewModel
import br.com.viewsystem_sample.core_ui.adapter.SampleGenericAdapter
import br.com.viewsystem_sample.core_ui.adapter.model.Foo
import br.com.viewsystem_sample.core_ui.base.fragment.BaseFragment
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidBottomWindowInset
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidEndWindowInset
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidStartWindowInset
import br.com.viewsystem_sample.core_ui.ext.onGetAndroidTopWindowInset
import br.com.viewsystem_sample.core_ui.ext.showToast
import br.com.viewsystem_sample.core_ui.ext.sumPadding
import br.com.viewsystem_sample.features.featureA.presentation.databinding.FragListBinding

class ListFragment private constructor() :
    BaseFragment<FragListBinding, ListViewModel>(FragListBinding::inflate) {

    private val listAdapter = SampleGenericAdapter()

    override fun setupViews() {
        setupListeners()
        setupObservers()
    }

    override fun setupEdgeToEdge() = with(binding) {
        root.onGetAndroidTopWindowInset {
            recyclerView.sumPadding(top = it)
        }
        root.onGetAndroidBottomWindowInset {
            recyclerView.sumPadding(bottom = it)
        }
        root.onGetAndroidStartWindowInset {
            recyclerView.sumPadding(start = it)
        }
        root.onGetAndroidEndWindowInset {
            recyclerView.sumPadding(start = it)
        }
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