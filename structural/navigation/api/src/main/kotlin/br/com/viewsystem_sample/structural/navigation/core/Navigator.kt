package br.com.viewsystem_sample.structural.navigation.core

/** Opens feature entry routes from the owning screen. Obtain a separate instance for each Activity. */
interface Navigator {

    /**
     * Opens a registered destination on the main thread.
     * Throws [IllegalStateException] if the route is unregistered or the owning screen has finished.
     */
    fun navigate(destination: FeatureDestination, options: NavigationOptions = NavigationOptions())

}
