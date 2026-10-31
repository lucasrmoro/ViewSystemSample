# featureA DI

Centralizes the feature's Koin registrations without `api` and `impl` submodules.
The app depends on `:features:featureA:di` and calls `FeatureAModule.init()` during
startup, after loading the structural DI modules.

Consumers use feature contracts directly; no intermediate interface loads DI.
Bindings remain in this module. Presentation, domain, and data modules do not depend
on it.

This module also registers `FeatureAAnalytics`, defined in `../analytics`, for injection
into the feature's layers. Load `AnalyticsModule.init()` before first resolving
that contract.
