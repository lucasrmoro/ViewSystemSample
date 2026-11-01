# Repository Guidelines

Modular Android project using Kotlin, XML layouts, View Binding, and runtime dependency injection.

## Architecture and modules

- `app/` starts the application. Keep all structural startup calls and DI module loading explicitly in `AppApplication`; factories and DI registrations must not start background work or initialize SDKs implicitly.
- `features/<feature>/` contains presentation, domain, data, analytics, and DI modules as needed. Every feature has its own `analytics` module. Repository contracts belong in `domain/api`; implementations and data sources belong in one `data` module. Domain code does not depend on persistence or vendor SDKs.
- Each feature has a single `di` module without an `api/impl` split. Declare bindings exclusively there; presentation only consumes dependencies. Inter-feature navigation contracts belong in `structural/navigation/api`; feature DI registers entry adapters with the shared registry. Keep entry implementations, Activities, Fragments, and XML navigation graphs inside feature presentation, without per-feature navigation Gradle modules.
- `structural/<name>/` contains shared services with `api`, `impl`, and `di` modules. `core` and `design-system` keep their main modules alongside `di`.
- Use Gradle `implementation` by default; use `api` only for intentionally re-exported contracts. Keep UI callbacks and adapter models in Views/Fragments instead of exposing them through ViewModel supertypes or state.
- Keep all DI vendor dependencies and types inside `structural/di/impl`. Consumers use `di/api` contracts for registration, qualifiers, parameters, and resolution. The foundational `di` service has only `api` and `impl`, without a self-registering DI module. AndroidX ViewModel contracts and delegation helpers belong in `di/api`; their implementation belongs in `di/impl`. Design-system depends only on the API, never the implementation.
- Feature layers depend on contracts: domain depends on domain APIs, and presentation depends on domain APIs rather than data or domain implementations. Shared structural services are consumed through their APIs. The app is the composition root and may access all layers; structural DI modules wire their own implementation. Keep implementations internal and avoid circular dependencies. Neither `api` nor `impl` depends on `di`.
- Structural APIs expose their own interfaces and models, preferring Kotlin and coroutines. Encapsulate platform types and SDKs in `impl`; screen lifecycle adapters belong in `design-system`.
- Before using a platform API or third-party library directly, check whether an existing structural service owns that responsibility. Consume its API contract; if it lacks a needed capability, extend the structural service rather than duplicating the behavior.
- Declare optional dependencies only in consumers that need them; do not impose them on base classes. Never retain an Activity in a singleton. Inject dispatchers into components that perform asynchronous work.
- Never place library clients, vendor SDKs, or integration adapters in `core`. Create a dedicated structural service with `api`, `impl`, and `di` whenever such an integration is needed; keep library details in `impl`.
- `build-logic/` contains convention plugins; dependency versions and coordinates belong in `gradle/libs.versions.toml`.

## Documentation

- Write all documentation in English, including READMEs, KDoc, architecture documents, and these guidelines.
- Each structural service has a root `README.md` describing its responsibility, DI, usage, and relevant behavior. Service implementation details belong in those READMEs, not in this file.
- Public structural interfaces require KDoc. Document behavior and conditions that are not evident from the signature; do not repeat names of constants or simple getters and setters.
- Features, including their `di` modules, do not require KDoc. Update affected documentation alongside code changes.

## Code and organization

- Use four spaces and Android Studio formatting, following existing code.
- Each structural DI module declares its registrations in a private `<service>Module = dependencyModule { ... }` property inside its module object. Its `init()` uses a single-line `loadModules(serviceModule)` call. Calls loading multiple feature modules keep one argument per line.
- Use PascalCase for types, camelCase for members, SNAKE_CASE for constants, and snake_case for Android resources. Preserve suffixes such as `ViewModel`, `Repository`, `UseCase`, and `Impl`; use `DTO` for transfer objects and `Entity` for entities in the `entity` package.
- Leave a blank line at the start and end of class and interface bodies; separate members with blank lines. Enums have no blank lines next to their braces.
- Prefer clear names over comments that describe the code. Name unused exceptions `_`.
- Every field in HTTP request/response DTOs, including nested DTOs, must declare `@JsonName("server_field_name")` from `network/api`. Never infer wire names from Kotlin property names; mappings must survive obfuscation.
- Kotlin sources belong in `src/main/kotlin/` and `src/<sourceSet>/kotlin/` for other variants. Resources belong in `src/main/res/`; local Kotlin tests belong in `src/test/kotlin/`.
- When moving or deleting files, remove directories that become empty, including empty ancestors.

## Build and validation

Use the Gradle Wrapper with JDK 17 and Android SDK 36. Configure the SDK through Android Studio or `local.properties`. The product flavors are `dev` and `prd`.

- `./gradlew :app:assembleDevDebug`: build the APK.
- `./gradlew :app:installDevDebug`: install on a device or emulator.
- `./gradlew :app:testDevDebugUnitTest`: run the app's local tests.
- `./gradlew :app:lintDevDebug`: run Android Lint.

Run checks proportional to the change and existing tests in affected modules. Use JUnit 4, the `Test` class suffix, and descriptive method names. Do not create instrumented tests. At this stage, do not add new unit tests unless the user requests them.

## Commits and pull requests

- Create commits only when requested. Review all pending changes, including new files, and separate independent objectives into consistent commits.
- Use short English messages in the format `type: description`, without a scope. Examples: `feat: add analytics provider` and `refactor: simplify dependency injection`.
- Use semantic types such as `feat`, `fix`, `refactor`, `docs`, `test`, `build`, `ci`, `perf`, `style`, and `chore`.
- For breaking changes, add `!` after the type and a `BREAKING CHANGE:` footer.
- In PRs, describe behavior, affected modules, and validation; link issues and include screenshots for visual changes.
