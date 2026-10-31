# Permissões

`permissions/api` expõe `PermissionsProvider`, `Permission` e `PermissionResult`.
O contrato aceita uma ou mais permissões sem expor Activity ou as APIs de plataforma.

O `app` depende de `permissions/di` e carrega `PermissionsModule.init()` antes de
abrir telas. Esse módulo registra uma factory por Activity, usando a função de
criação de `permissions/impl`. A implementação usa Activity Result API e deve ser
obtida em `onCreate`, antes de `STARTED`, como ocorre em `BaseActivity`. Activity
não é armazenada em singleton.

`requestPermissions(vararg permissions)` preserva a ordem dos argumentos e processa
uma permissão por vez. Novos pedidos entram em uma fila de lotes; cada callback é
preservado, inclusive quando ele enfileira outra solicitação. Os resultados incluem
`GRANTED`, `DENIED`, `NOT_ASK_AGAIN`, `CANCELLED` e `NOT_REQUIRED`.

As permissões de câmera e notificações também possuem métodos de conveniência.
A permissão de notificações retorna `NOT_REQUIRED` nas versões anteriores ao Android
13. A versão do sistema é consultada diretamente na implementação.
