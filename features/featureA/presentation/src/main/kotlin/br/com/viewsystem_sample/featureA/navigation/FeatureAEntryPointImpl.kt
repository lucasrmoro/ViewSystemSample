package br.com.viewsystem_sample.featureA.navigation

import android.content.Context
import android.content.Intent
import br.com.viewsystem_sample.structural.navigation.featureA.FeatureADestination
import br.com.viewsystem_sample.structural.navigation.core.FeatureEntryPoint
import br.com.viewsystem_sample.featureA.activity.FeatureAActivity

fun createFeatureAEntryPoint(): FeatureEntryPoint<FeatureADestination> = FeatureAEntryPointImpl()

internal class FeatureAEntryPointImpl : FeatureEntryPoint<FeatureADestination> {

    override val destinationType = FeatureADestination::class

    override fun createIntent(context: Context, destination: FeatureADestination): Intent =
        Intent(context, FeatureAActivity::class.java)

}
