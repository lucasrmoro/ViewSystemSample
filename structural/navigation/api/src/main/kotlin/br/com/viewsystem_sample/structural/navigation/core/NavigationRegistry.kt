package br.com.viewsystem_sample.structural.navigation.core

/** Stores feature entry adapters for the application lifetime, without retaining screens. */
interface NavigationRegistry {

    /** Registers a route during application setup; duplicate destination types are rejected. */
    fun <D : FeatureDestination> register(entryPoint: FeatureEntryPoint<D>)

}
