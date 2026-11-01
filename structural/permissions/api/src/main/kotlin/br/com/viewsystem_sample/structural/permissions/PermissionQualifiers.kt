package br.com.viewsystem_sample.structural.permissions

import br.com.viewsystem_sample.structural.di.DependencyQualifier

/** Identifies permission provider bindings with different context capabilities. */
object PermissionQualifiers {

    /** Selects the application-context provider, which is intended for permission checks only. */
    object ApplicationContext : DependencyQualifier {

        override val value: String = "permissions_application_context"

    }

}
