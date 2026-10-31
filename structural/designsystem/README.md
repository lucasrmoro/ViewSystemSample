# Design system

Este módulo reúne componentes de interface em XML, adapters, extensões de views e
classes base de apresentação. Ele também adapta os contratos dos estruturantes
ao ciclo de vida das telas. As features consomem essas adaptações sem colocar
dependências de Activity nos contratos dos estruturantes.

## BaseActivity

`BaseActivity` configura o View Binding, o ViewModel e a observação do estado do
aplicativo. Os bindings Koin dos estruturantes devem estar carregados pelo `app`
antes de criar a Activity.

Os providers de permissões e biometria são obtidos por Activity em `onCreate`.
O provider de biometria é adaptado por `LifecycleBiometricProvider`: a operação
é cancelada quando a coroutine chamadora é cancelada ou a tela é destruída.
Dispare `enroll()` e `authenticate()` com a tela em `RESUMED`, por exemplo em
resposta a um clique. O estado do aparelho pode ser consultado por `getStatus()`.

O módulo usa os contratos dos estruturantes e o `Logger` injetado. Os bindings de
biometria, permissões, estado do app e log pertencem às respectivas implementações,
carregadas na inicialização do aplicativo.

Para criptografia, armazenamento e recuperação dos valores, consulte os READMEs de
[biometria](../biometric/README.md) e [local storage](../local-storage/README.md).
