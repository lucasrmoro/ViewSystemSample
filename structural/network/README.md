# Network

`network/api` contains contracts, models, shared exceptions, Retrofit qualifiers,
and consumer extensions. The API does not depend on `core`. `network/impl` contains
connectivity, the HTTP interceptor, and the authenticator.

The app depends on `network/di` and loads `CoreNetworkModule.init()` before resolving
services. This module configures OkHttp clients, Retrofit, and their qualifiers,
registers the connectivity contract, and connects HTTP implementation components.
Factory functions expose internal classes through their contracts.

Authenticated bindings use `NetworkInterceptor` and an authenticator that consumes
`LoginRepository` from `login/domain/api` to refresh tokens. Unauthenticated bindings
do not install that authenticator. Load the login DI module before resolving a
client that needs token refresh.

Public `RetrofitAuth` and `RetrofitNoAuth` qualifiers remain in `api`, alongside
`DependencyResolver.retrofit` and `DependencyResolver.retrofitNoAuth`. Converter and timeout configuration
belongs in `di`. The implementation neither depends on DI nor declares DI bindings.
