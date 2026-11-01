package br.com.viewsystem_sample.modules

object Modules {
    const val COMMON_DOMAIN = ":common:domain"

    object Structural {
        private const val ROOT = ":structural"

        const val ANALYTICS_API = "$ROOT:analytics:api"
        const val ANALYTICS_IMPL = "$ROOT:analytics:impl"
        const val ANALYTICS_DI = "$ROOT:analytics:di"
        const val REMOTE_CONFIG_API = "$ROOT:remote-config:api"
        const val REMOTE_CONFIG_IMPL = "$ROOT:remote-config:impl"
        const val REMOTE_CONFIG_DI = "$ROOT:remote-config:di"
        const val NOTIFICATIONS_API = "$ROOT:notifications:api"
        const val NOTIFICATIONS_IMPL = "$ROOT:notifications:impl"
        const val NOTIFICATIONS_DI = "$ROOT:notifications:di"
        const val PUSH_NOTIFICATIONS_API = "$ROOT:push-notifications:api"
        const val PUSH_NOTIFICATIONS_IMPL = "$ROOT:push-notifications:impl"
        const val PUSH_NOTIFICATIONS_DI = "$ROOT:push-notifications:di"

        const val NAVIGATION_API = "$ROOT:navigation:api"
        const val NAVIGATION_IMPL = "$ROOT:navigation:impl"
        const val NAVIGATION_DI = "$ROOT:navigation:di"

        const val DI_API = "$ROOT:di:api"
        const val DI_IMPL = "$ROOT:di:impl"

        const val CORE = "$ROOT:core"
        const val CORE_DI = "$ROOT:core:di"
        const val BIOMETRIC_API = "$ROOT:biometric:api"
        const val BIOMETRIC_IMPL = "$ROOT:biometric:impl"
        const val BIOMETRIC_DI = "$ROOT:biometric:di"
        const val SESSION_API = "$ROOT:session:api"
        const val SESSION_IMPL = "$ROOT:session:impl"
        const val SESSION_DI = "$ROOT:session:di"
        const val PERMISSIONS_API = "$ROOT:permissions:api"
        const val PERMISSIONS_IMPL = "$ROOT:permissions:impl"
        const val PERMISSIONS_DI = "$ROOT:permissions:di"
        const val LOGGER_API = "$ROOT:logger:api"
        const val LOGGER_IMPL = "$ROOT:logger:impl"
        const val LOGGER_DI = "$ROOT:logger:di"
        const val NETWORK_API = "$ROOT:network:api"
        const val NETWORK_IMPL = "$ROOT:network:impl"
        const val NETWORK_DI = "$ROOT:network:di"
        const val DESIGN_SYSTEM = "$ROOT:design-system"
        const val DESIGN_SYSTEM_DI = "$ROOT:design-system:di"
        const val LOCAL_STORAGE_API = "$ROOT:local-storage:api"
        const val LOCAL_STORAGE_IMPL = "$ROOT:local-storage:impl"
        const val LOCAL_STORAGE_DI = "$ROOT:local-storage:di"
    }

    object Feature {
        private const val ROOT = ":features"

        object Login {
            private const val LOGIN = "$ROOT:login"

            const val ANALYTICS = "$LOGIN:analytics"
            const val DOMAIN_API = "$LOGIN:domain:api"
            const val DATA = "$LOGIN:data"
            const val DI = "$LOGIN:di"
        }

        object FeatureA {
            private const val FEATURE_A = "$ROOT:featureA"

            const val ANALYTICS = "$FEATURE_A:analytics"
            const val DI = "$FEATURE_A:di"
            const val DATA = "$FEATURE_A:data"
            const val DOMAIN_API = "$FEATURE_A:domain:api"
            const val DOMAIN_IMPL = "$FEATURE_A:domain:impl"
            const val PRESENTATION = "$FEATURE_A:presentation"
        }
    }
}
