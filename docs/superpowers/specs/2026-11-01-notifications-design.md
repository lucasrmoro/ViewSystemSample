# Notificações Android e Push do Firebase

## Objetivo

Adicionar dois serviços estruturais seguindo a organização `api` / `impl` / `di`
do repositório. `notifications` será responsável pelos canais Android e pela
exibição local. `push-notifications` será responsável pelo token e pelo
recebimento de mensagens do Firebase Cloud Messaging (FCM), delegando a exibição
ao serviço `notifications`.

## Objetivos e restrições

- Manter os tipos dos SDKs Android e Firebase dentro dos módulos de implementação.
- Expor contratos Kotlin nas APIs e documentar com KDoc as interfaces estruturais
  públicas.
- Declarar bindings somente no módulo DI único de cada serviço; fábricas e
  registros DI não inicializam SDKs nem iniciam trabalho em segundo plano.
- Manter a inicialização explícita em `AppApplication` e adicionar constantes de
  módulos, inclusões no settings e dependências da composição do app seguindo os
  serviços estruturais existentes.
- Documentar responsabilidades, DI, uso, inicialização e configuração no README
  de cada serviço.
- Não adicionar configuração de projeto Firebase nem novos testes.

## Abordagens consideradas

1. `notifications` cuida da construção e exibição de notificações locais;
   `push-notifications` cuida dos tokens FCM e das mensagens recebidas, delegando
   a exibição. **Escolhida:** separa a exibição da plataforma do transporte push
   e permite notificações locais sem Firebase.
2. `push-notifications` também constrói e exibe notificações. É mais simples para
   consumidores que usam somente push, mas acopla notificações locais ao FCM e
   reduz a reutilização do serviço de notificações.

## Desenho

`notifications/api` define um pedido de notificação independente da plataforma,
com identificador, nome e descrição do canal, e um provider para exibi-lo. O
provider garante que o canal exista durante `show()`. A implementação usa as APIs
de notificação do Android, e o módulo DI registra o provider. As permissões
Android necessárias são consultadas por `PermissionsProvider`; o fluxo de pedido
de permissão continua sob responsabilidade de quem possui uma Activity.

`push-notifications/api` define um provider para obter o token FCM atual e um
contrato de tratamento de mensagens no nível do app. `push-notifications/impl`
contém a integração com o SDK Firebase e um adaptador `FirebaseMessagingService`.
Para mensagens com conteúdo exibível, o adaptador as converte em pedidos de
notificação local e chama `notifications`. O módulo DI registra o provider push e
o handler de mensagens com suas dependências necessárias. A inicialização e a
declaração do serviço Android são explícitas; fábricas DI não inicializam Firebase
nem executam operações de rede.

O trabalho seguirá as convenções do repositório para versões de dependências,
manifests, nomes de pacotes e ordem de carregamento dos módulos em
`AppApplication`. A configuração Firebase continuará sob responsabilidade de
quem integra o app, conforme a documentação de Analytics.

## Fora do escopo

Não serão adicionados UI para solicitar permissão de notificação, navegação ao
tocar numa notificação, API de inscrição em tópicos, integração com servidor,
arquivo de configuração Firebase nem eventos de analytics. Os chamadores serão
responsáveis por solicitar a permissão de execução quando exigida pela versão do
Android.

## Critérios de conclusão

Os dois serviços poderão ser consumidos separadamente por suas APIs; tipos
Firebase não escaparão dos módulos de implementação; a exibição local poderá ser
usada por consumidores com ou sem push; e a inicialização do app seguirá o padrão
explícito de composição já existente.
