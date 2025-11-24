package br.com.viewsystem_sample.featureA.fragment

import br.com.viewsystem_sample.core_ui.base.fragment.BaseFragment
import br.com.viewsystem_sample.featureA.viewModel.FormViewModel
import br.com.viewsystem_sample.features.featureA.presentation.databinding.FragFormBinding

internal class FormFragment() :
    BaseFragment<FragFormBinding, FormViewModel>(FragFormBinding::inflate) {

    override fun setupViews() {
        setupListeners()
    }

    override fun setupListeners() = with(binding) {
        btnSave.setOnClickListener {
            btnSave.isLoading = true
            popBackStack()
        }
    }
}