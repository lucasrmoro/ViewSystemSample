# Rede

`network/api` reúne contratos, modelos, exceções compartilhadas, qualificadores
Retrofit e extensões usadas pelos consumidores. A API não depende de `core`.
`network/impl` contém conectividade, interceptor e authenticator HTTP.

O `app` depende de `network/di` e carrega `CoreNetworkModule.init()` antes de resolver
serviços. Esse módulo configura clientes OkHttp, Retrofit e seus qualificadores,
registra o contrato de conectividade e conecta os componentes HTTP da implementação.
As classes internas são acessadas por funções de criação que retornam contratos.

Os bindings com autenticação usam `NetworkInterceptor` e o authenticator que consome
`LoginRepository`, de `login/domain/api`, para renovar tokens. Os bindings sem
autenticação não instalam esse authenticator. O módulo de DI de login deve estar
carregado antes de resolver o cliente que precisa renovar tokens.

Os qualificadores públicos `RetrofitAuth` e `RetrofitNoAuth` permanecem em `api`,
junto das extensões `Scope.retrofit` e `Scope.retrofitNoAuth`. As configurações de
conversão e timeout pertencem a `di`. `impl` não depende desse módulo nem declara
bindings Koin.
