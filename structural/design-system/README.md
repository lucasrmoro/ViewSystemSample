# Design system

Contains XML UI components, adapters, view extensions, and presentation base classes.
It also adapts structural contracts to screen lifecycles. Features consume these
adapters without adding Activity dependencies to structural contracts.

## BaseActivity

`BaseActivity` configures View Binding, the ViewModel, and application state
observation. The permissions provider is obtained per Activity in `onCreate`, after
loading structural DI modules. The base class does not depend on biometrics or
create a biometric provider for every screen.

## Biometrics in consuming screens

Screens that use biometrics declare a dependency on `biometric/api` and obtain
their own `BiometricProvider`. The app loads `BiometricModule.init()` and
`DesignSystemModule.init()` before opening those screens. The main design system
module does not re-export the biometric API.

The `ScreenBiometricProvider` binding in `design-system/di` adapts the provider from
`biometric/di` to a screen lifecycle. Screens can obtain it in `onCreate` using
the DI backend and passing their Activity and `lifecycleScope`:

```kotlin
val biometricProvider: BiometricProvider = get(ScreenBiometricProvider) {
    parametersOf(this, lifecycleScope)
}
```

The adapter cancels an operation when its calling coroutine is cancelled or the
screen is destroyed. Call `enroll()` and `authenticate()` while the screen is
`RESUMED`, for example in response to a click. `getStatus()` checks device capability
without opening a prompt.

This module consumes structural contracts and an injected `Logger`. Bindings for
biometrics, permissions, app state, and logging belong to their respective DI
modules, loaded at app startup. The main design system module consumes the DI API but
declares no registrations and does not depend on `design-system/di`.

For encryption, persistence, and retrieval, see the [biometric](../biometric/README.md)
and [local storage](../local-storage/README.md) READMEs.

## Dependency injection and ViewModels

Screen and view components consume `structural/di/api` for resolution and lazy
injection. BaseActivity and BaseFragment use the ViewModel adapters provided by
`structural/di/api`, which delegate to the `DependencyViewModelProvider` contract.
The AndroidX factory implementation belongs in `di/impl` and is registered during
application bootstrap. Design-system does not depend on `di/impl` or import Koin.

The presentation convention plugin keeps core and design-system as Gradle
`implementation` dependencies. Fragments implement UI callback contracts and map
domain values into adapter items. ViewModels expose state and actions without
implementing RecyclerView callbacks or returning design-system adapter models.

Navigation between features belongs to `structural/navigation`. Navigation helpers
here operate on the consuming feature's internal fragment graph.
