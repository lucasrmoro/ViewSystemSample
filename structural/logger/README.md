# Logger

`logger/api` expõe o contrato `Logger`. Os consumidores recebem esse contrato por
injeção; as extensões de Flow permanecem independentes do logger e de Koin.

O `app` depende de `logger/di` e carrega `LoggerModule.init()`. Esse módulo registra
uma instância compartilhada por meio da função de criação de `logger/impl`.
A classe de implementação permanece interna e não contém registros Koin.

Em debug, `impl` usa Timber por `debugImplementation` e mantém o código em
`src/debug`. Em release, `src/release` fornece a implementação vazia. O módulo
`di` não depende de Timber e não há flag de execução para habilitar esses logs.
