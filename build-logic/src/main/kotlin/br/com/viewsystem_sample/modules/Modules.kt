package br.com.viewsystem_sample.modules

object Modules {
    const val COMMON_DOMAIN = ":common:domain"

    object Structural {
        private const val ROOT = ":structural"

        const val CORE = "$ROOT:core"
        const val NETWORK_API = "$ROOT:network:api"
        const val NETWORK_IMPL = "$ROOT:network:impl"
        const val DESIGNSYSTEM = "$ROOT:designsystem"
        const val LOCAL_STORAGE_API = "$ROOT:local-storage:api"
        const val LOCAL_STORAGE_IMPL = "$ROOT:local-storage:impl"
    }

    object Feature {
        private const val ROOT = ":features"

        object Login {
            private const val LOGIN = "$ROOT:login"

            const val DOMAIN_API = "$LOGIN:domain:api"
            const val DATA = "$LOGIN:data"
            const val DI_API = "$LOGIN:di:api"
            const val DI_IMPL = "$LOGIN:di:impl"
        }

        object FeatureA {
            private const val FEATURE_A = "$ROOT:featureA"

            const val DI_API = "$FEATURE_A:di:api"
            const val DI_IMPL = "$FEATURE_A:di:impl"
            const val DATA = "$FEATURE_A:data"
            const val DOMAIN_API = "$FEATURE_A:domain:api"
            const val DOMAIN_IMPL = "$FEATURE_A:domain:impl"
            const val PRESENTATION = "$FEATURE_A:presentation"
            const val NAVIGATION_API = "$FEATURE_A:navigation:api"
        }
    }
}