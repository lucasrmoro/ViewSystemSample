package br.com.viewsystem_sample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.viewsystem_sample.structural.di.inject
import br.com.viewsystem_sample.structural.di.parametersOf
import br.com.viewsystem_sample.structural.navigation.featureA.FeatureADestination
import br.com.viewsystem_sample.structural.navigation.core.NavigationOptions
import br.com.viewsystem_sample.structural.navigation.core.Navigator

class LauncherActivity : AppCompatActivity() {

    private val navigator by inject<Navigator> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.navigate(
            destination = FeatureADestination,
            options = NavigationOptions(
                showActivityTransition = false,
                finishCurrentActivity = true
            )
        )
    }

}
