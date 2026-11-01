package br.com.viewsystem_sample.structural.navigation.core

import android.app.Activity

/** Opens feature entry routes from the owning screen. */
interface Navigator {

    /**
     * Opens a registered destination on the main thread using [activity] as the navigation host.
     * Throws [IllegalStateException] if [activity] is null or inactive, or the route is unregistered.
     */
    fun navigate(
        activity: Activity?,
        destination: FeatureDestination,
        options: NavigationOptions = NavigationOptions()
    )

}
