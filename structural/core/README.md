# Core

Contains shared contracts and utilities, Flow extensions, and application state
aggregation through `AppStateProvider`.

`core/di` registers qualified dispatchers and `AppStateProvider`. The app loads
`CoreModule.init()` before resolving consumers. Session and connectivity contracts
come from `session/api` and `network/api`; their implementations are registered by
the corresponding DI modules.

`core` does not depend on `core/di`. The `DispatcherIO`, `DispatcherMain`, and
`DispatcherDefault` qualifiers and `Scope` extensions stay in the main module so
other DI layers can use them without circular dependencies. Implementations receive
dispatchers through constructors instead of accessing `Dispatchers` directly;
concrete values are selected in `core/di`.

Library clients, vendor SDKs, and their adapters belong in dedicated structural
services, never in `core`.
