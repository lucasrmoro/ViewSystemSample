# Sessão

`session/api` expõe `AppSessionProvider` e `SessionStatus`. A implementação mantém
o estado de expiração da sessão, observado pelo agregador de estado do `core`.

O `app` depende de `session/di` e carrega `SessionModule.init()` antes de usar os
contratos. O módulo `di` registra um `AppSessionProvider` singleton usando a função
de criação de `session/impl`. A implementação permanece interna.

Os consumidores dependem de `api`. Nem a API nem a implementação dependem do módulo
`di`; os registros Koin ficam exclusivamente nele. A chamada `expireSession()`
publica a expiração para os observadores existentes.
