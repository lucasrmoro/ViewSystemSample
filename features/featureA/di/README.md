# featureA DI

Centralizes the feature's DI registrations without `api` and `impl` submodules.
The app depends on `:features:featureA:di` and calls `FeatureAModule.init(navigationRegistry = get())` during
startup, after loading the structural DI modules.

Consumers use feature contracts directly; no intermediate interface loads DI.
Bindings remain in this module. Presentation, domain, and data modules do not depend
on it.

This module also registers `FeatureAAnalytics`, defined in `../analytics`, for injection
into the feature's layers. Load `AnalyticsModule.init()` before first resolving
that contract.

Load `NavigationModule.init()` first. This feature registers its entry adapter with
the shared registry; its Activity and fragment graph remain in presentation.
