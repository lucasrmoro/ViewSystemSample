package br.com.viewsystem_sample.structural.navigation.core

import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

/**
 * Android registration bridge implemented by feature presentation and registered by feature DI.
 * Implementations must not retain a Context or Activity; consumers navigate through [Navigator].
 */
interface FeatureEntryPoint<D : FeatureDestination> {

    val destinationType: KClass<D>

    /** Builds an explicit intent for the feature's private Activity without starting it. */
    fun createIntent(context: Context, destination: D): Intent

}
