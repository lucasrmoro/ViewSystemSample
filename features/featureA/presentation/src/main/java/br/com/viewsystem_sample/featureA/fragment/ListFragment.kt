package br.com.viewsystem_sample.featureA.fragment

import androidx.core.view.updatePadding
import androidx.navigation.fragment.findNavController
import br.com.viewsystem_sample.structural.designsystem.adapter.SampleGenericAdapter
import br.com.viewsystem_sample.structural.designsystem.adapter.model.Foo
import br.com.viewsystem_sample.structural.designsystem.base.fragment.BaseFragment
import br.com.viewsystem_sample.structural.designsystem.ext.navigateAnimated
import br.com.viewsystem_sample.structural.designsystem.ext.showToast
import br.com.viewsystem_sample.featureA.viewModel.ListViewModel
import br.com.viewsystem_sample.features.featureA.presentation.R
import br.com.viewsystem_sample.features.featureA.presentation.databinding.FragListBinding

internal class ListFragment() :
    BaseFragment<FragListBinding, ListViewModel>(FragListBinding::inflate) {

    private val listAdapter = SampleGenericAdapter()

    override fun setupViews() {
        setupListeners()
        setupObservers()
        showScreenLoading()
        binding.recyclerView.resetCurrentPage()
        viewModel.loadItems()
    }

    override fun setupEdgeToEdge() = with(binding) {
        onGetAndroidWindowInsets { top, bottom, left, right ->
            recyclerView.updatePadding(bottom = bottom, left = left, right = right)
        }
    }

    override fun setupListeners() = with(binding) {
        btnAdd.setOnClickListener {
            findNavController().navigateAnimated(R.id.action_list_fragment_to_form_fragment)
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
            showToast(result.second ?: genericErrorMessage)
        }
    }
}