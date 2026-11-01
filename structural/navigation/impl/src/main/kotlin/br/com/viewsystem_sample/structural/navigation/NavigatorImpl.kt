package br.com.viewsystem_sample.structural.navigation

import android.app.Activity
import android.os.Looper
import br.com.viewsystem_sample.structural.core.ext.launchActivity
import br.com.viewsystem_sample.structural.navigation.core.FeatureDestination
import br.com.viewsystem_sample.structural.navigation.core.NavigationOptions
import br.com.viewsystem_sample.structural.navigation.core.Navigator

internal class NavigatorImpl(
    private val registry: NavigationRegistryImpl
) : Navigator {

    override fun navigate(
        activity: Activity?,
        destination: FeatureDestination,
        options: NavigationOptions
    ) {
        check(Looper.myLooper() == Looper.getMainLooper()) { "Navigation requires the main thread" }
        checkNotNull(activity) { "Navigation requires an Activity" }
        check(!activity.isFinishing && !activity.isDestroyed) { "Navigation requires an active Activity" }
        val intent = registry.createIntent(activity, destination)
        activity.launchActivity(
            intent = intent,
            showActivityTransition = options.showActivityTransition,
            finishCurrentActivity = options.finishCurrentActivity
        )
    }

}
