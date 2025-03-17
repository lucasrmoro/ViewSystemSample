package br.com.viewsystem_sample.featurea.presentation.activity

import android.os.Bundle
import br.com.viewsystem_sample.core_ui.base.activity.BaseContainerActivity
import br.com.viewsystem_sample.featurea.di.FeatureAModule
import br.com.viewsystem_sample.featurea.presentation.fragment.ListFragment
import br.com.viewsystem_sample.featurea.presentation.viewModel.FeatureAViewModel

internal class FeatureAActivity : BaseContainerActivity<FeatureAViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureAModule.init()
        navigate(ListFragment.newInstance())
    }

}