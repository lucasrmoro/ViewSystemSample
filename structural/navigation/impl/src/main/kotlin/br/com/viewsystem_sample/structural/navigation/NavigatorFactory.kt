package br.com.viewsystem_sample.structural.navigation

import br.com.viewsystem_sample.structural.navigation.core.NavigationRegistry
import br.com.viewsystem_sample.structural.navigation.core.Navigator

class NavigatorFactory {

    private val navigationRegistry = NavigationRegistryImpl()

    val registry: NavigationRegistry = navigationRegistry

    fun create(): Navigator = NavigatorImpl(navigationRegistry)

}
