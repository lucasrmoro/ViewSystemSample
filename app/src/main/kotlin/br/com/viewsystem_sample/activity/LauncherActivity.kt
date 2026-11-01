package br.com.viewsystem_sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.viewsystem_sample.featureA.navigation.FeatureAEntryPoint
import br.com.viewsystem_sample.structural.core.ext.launchActivity
import br.com.viewsystem_sample.structural.di.inject

class LauncherActivity : AppCompatActivity() {

    private val featureAEntryPoint by inject<FeatureAEntryPoint>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchActivity(
            intent = featureAEntryPoint.createIntent(this),
            showActivityTransition = false,
            finishCurrentActivity = true
        )
    }

}
