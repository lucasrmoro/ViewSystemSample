package br.com.viewsystem_sample.modules

object Modules {
    const val CORE = ":core"
    const val CORE_NETWORK = ":core-network"
    const val CORE_UI = ":core-ui"
    const val COMMON_DOMAIN = ":common:domain"
    const val LOCAL_STORAGE = ":local-storage"

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

    const val ENTITIES = ":entities"
}