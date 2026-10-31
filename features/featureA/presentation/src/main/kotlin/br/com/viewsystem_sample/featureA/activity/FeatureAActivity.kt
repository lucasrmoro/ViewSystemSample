package br.com.viewsystem_sample.featureA.activity

import br.com.viewsystem_sample.featureA.viewModel.FeatureAViewModel
import br.com.viewsystem_sample.features.featureA.presentation.R
import br.com.viewsystem_sample.structural.designsystem.base.activity.BaseContainerActivity

internal class FeatureAActivity : BaseContainerActivity<FeatureAViewModel>() {

    override fun navigationRes(): Int = R.navigation.nav_graph

}
