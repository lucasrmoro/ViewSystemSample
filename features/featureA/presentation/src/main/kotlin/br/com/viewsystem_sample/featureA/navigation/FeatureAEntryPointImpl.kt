package br.com.viewsystem_sample.featureA.navigation

import android.content.Context
import android.content.Intent
import br.com.viewsystem_sample.featureA.activity.FeatureAActivity

fun createFeatureAEntryPoint(): FeatureAEntryPoint = FeatureAEntryPointImpl()

internal class FeatureAEntryPointImpl : FeatureAEntryPoint {

    override fun createIntent(context: Context): Intent =
        Intent(context, FeatureAActivity::class.java)

}
