# Navigation

Routes between features through their entry Activities. Fragments and XML navigation
graphs remain private to each feature and continue using `NavController`.

## Modules

- `api/core`: navigation contracts, options, and entry registration.
- `api/<feature>`: typed destinations grouped by feature, such as `featureA/FeatureADestination`.
- `impl`: route lookup and Android Activity navigation.
- `di`: application registry and screen-specific navigator factories.

Consumers depend only on `:structural:navigation:api`. Feature presentation implements
`FeatureEntryPoint<D>` using its internal Activity and exposes a creation function.
The Android `Context` and `Intent` in that registration bridge are only for wiring;
callers use the platform-independent `Navigator` contract.

## Setup

Load `NavigationModule.init()` in `AppApplication` before registering feature entries.
Pass the shared `NavigationRegistry` to feature DI during application setup:

```kotlin
NavigationModule.init()
FeatureAModule.init(navigationRegistry = get())
```

Feature DI registers `createFeatureAEntryPoint()` with that registry. Registration
rejects duplicate destination types. The registry retains adapters for the application
lifetime; adapters must never retain an Activity or Context.

## Usage

Resolve a navigator only in screens that need to open another feature:

```kotlin
private val navigator by inject<Navigator> { parametersOf(this) }

navigator.navigate(FeatureADestination)
```

Here `this` is the consuming Activity. A Fragment can supply `requireActivity()`.
Navigator is a factory binding, not a singleton: keep each instance with its owning
screen and do not store it in a ViewModel or application-scoped component.

Navigation runs on the main thread and requires an Activity that has not finished
or been destroyed. Missing registrations are configuration errors and throw
`IllegalStateException`. By default the current Activity remains in the back stack
and the platform transition is used. `NavigationOptions` can disable the launch
animation or finish the caller, as used by `LauncherActivity`.

Add routes as implementations of `FeatureDestination` in this API. Routes can carry
typed arguments; the matching entry adapter translates them into Activity extras.
Do not add a navigation Gradle module to each feature.
