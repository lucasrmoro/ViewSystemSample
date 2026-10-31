# DI de login

Este módulo concentra os registros Koin da feature, sem submódulos `api` e `impl`.
O `app` depende de `:features:login:di` e chama `LoginModule.init()` durante
sua inicialização, depois de carregar os módulos estruturantes.

Os consumidores usam os contratos da feature; não há interface intermediária para
carregar a DI. Os bindings permanecem neste módulo, e os módulos de apresentação,
domínio e dados não dependem dele.
