# Core

Este módulo mantém contratos e utilitários compartilhados, as extensões de Flow e
a agregação de estado da aplicação por `AppStateProvider`.

`core/di` registra os dispatchers qualificados e o `AppStateProvider`. O `app`
carrega `CoreModule.init()` antes de resolver consumidores. Sessão e conectividade
são fornecidas por `session/api` e `network/api`; suas implementações são registradas
pelos respectivos módulos `di`.

`core` não depende de `core/di`. Os qualificadores `DispatcherIO`, `DispatcherMain`
e `DispatcherDefault`, junto das extensões de `Scope`, permanecem no módulo principal
para serem usados por outras camadas de DI sem dependências circulares. As
implementações recebem dispatchers pelo construtor, sem acessar `Dispatchers`
diretamente; os valores concretos são escolhidos em `core/di`.
