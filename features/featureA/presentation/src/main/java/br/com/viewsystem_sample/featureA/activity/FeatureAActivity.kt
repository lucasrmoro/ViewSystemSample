package br.com.viewsystem_sample.featureA.activity

import android.os.Bundle
import br.com.viewsystem_sample.featureA.FeatureADI
import br.com.viewsystem_sample.core_ui.base.activity.BaseContainerActivity
import br.com.viewsystem_sample.featureA.fragment.ListFragment
import br.com.viewsystem_sample.featureA.viewModel.FeatureAViewModel
import org.koin.android.ext.android.inject

internal class FeatureAActivity : BaseContainerActivity<FeatureAViewModel>() {

    private val featureADI by inject<FeatureADI>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureADI.load()
        navigate(ListFragment.newInstance())
    }

}