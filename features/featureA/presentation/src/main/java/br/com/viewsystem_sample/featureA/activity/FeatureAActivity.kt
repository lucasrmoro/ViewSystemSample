package br.com.viewsystem_sample.featureA.activity

import android.os.Bundle
import br.com.viewsystem_sample.featureA.FeatureADI
import br.com.viewsystem_sample.structural.designsystem.base.activity.BaseContainerActivity
import br.com.viewsystem_sample.featureA.fragment.ListFragment
import br.com.viewsystem_sample.featureA.viewModel.FeatureAViewModel
import br.com.viewsystem_sample.features.featureA.presentation.R
import org.koin.android.ext.android.inject

internal class FeatureAActivity : BaseContainerActivity<FeatureAViewModel>() {

    private val featureADI by inject<FeatureADI>()

    override fun navigationRes(): Int = R.navigation.nav_graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        featureADI.load()
    }

}