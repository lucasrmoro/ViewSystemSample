package br.com.viewsystem_sample.modules

object Modules {
    const val COMMON_DOMAIN = ":common:domain"

    object Structural {
        private const val ROOT = ":structural"

        const val CORE = "$ROOT:core"
        const val NETWORK = "$ROOT:network"
        const val DESIGNSYSTEM = "$ROOT:designsystem"
        const val ENTITIES = "$ROOT:entities"
        const val LOCAL_STORAGE = "$ROOT:local-storage"
    }

    object Feature {
        private const val ROOT = ":features"

        object FeatureA {
            private const val FEATURE_A = "$ROOT:featureA"

            const val DI_API = "$FEATURE_A:di:api"
            const val DI_IMPL = "$FEATURE_A:di:impl"
            const val DATA_API = "$FEATURE_A:data:api"
            const val DATA_IMPL = "$FEATURE_A:data:impl"
            const val DOMAIN_API = "$FEATURE_A:domain:api"
            const val DOMAIN_IMPL = "$FEATURE_A:domain:impl"
            const val PRESENTATION = "$FEATURE_A:presentation"
        }
    }
}