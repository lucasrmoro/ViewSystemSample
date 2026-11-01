package br.com.viewsystem_sample.structural.navigation

import android.content.Context
import android.content.Intent
import br.com.viewsystem_sample.structural.navigation.core.FeatureDestination
import br.com.viewsystem_sample.structural.navigation.core.FeatureEntryPoint
import br.com.viewsystem_sample.structural.navigation.core.NavigationRegistry
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

internal class NavigationRegistryImpl : NavigationRegistry {

    private val routes =
        ConcurrentHashMap<KClass<out FeatureDestination>, (Context, FeatureDestination) -> Intent>()

    override fun <D : FeatureDestination> register(entryPoint: FeatureEntryPoint<D>) {
        val route: (Context, FeatureDestination) -> Intent = { context, destination ->
            val typedDestination = checkNotNull(entryPoint.destinationType.java.cast(destination))
            entryPoint.createIntent(context, typedDestination)
        }
        check(routes.putIfAbsent(entryPoint.destinationType, route) == null) {
            "Destination already registered: ${entryPoint.destinationType}"
        }
    }

    fun createIntent(context: Context, destination: FeatureDestination): Intent {
        val route = checkNotNull(routes[destination::class]) {
            "Destination not registered: ${destination::class}"
        }
        return route(context, destination)
    }

}
