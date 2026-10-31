# Design system

Este módulo reúne componentes de interface em XML, adapters, extensões de views e
classes base de apresentação. Ele também adapta os contratos dos estruturantes
ao ciclo de vida das telas. As features consomem essas adaptações sem colocar
dependências de Activity nos contratos dos estruturantes.

## BaseActivity

`BaseActivity` configura o View Binding, o ViewModel e a observação do estado do
aplicativo. O provider de permissões é obtido por Activity em `onCreate`, depois
de carregar os módulos estruturantes. A classe base não depende de biometria nem
cria um provider biométrico para todas as telas.

## Biometria nas telas consumidoras

A tela que usar biometria declara sua dependência de `biometric/api` e obtém seu
próprio `BiometricProvider`. O app carrega `BiometricModule.init()` e
`DesignSystemModule.init()` antes de abrir essas telas. O módulo principal de
design system não reexporta a API de biometria.

O binding `ScreenBiometricProvider`, de `designsystem/di`, adapta o provider de
`biometric/di` ao ciclo de vida da tela. A tela pode obtê-lo em `onCreate`, usando
Koin e passando sua Activity e seu `lifecycleScope`:

```kotlin
val biometricProvider: BiometricProvider = get(ScreenBiometricProvider) {
    parametersOf(this, lifecycleScope)
}
```

A adaptação cancela a operação quando a coroutine chamadora é cancelada ou a tela
é destruída. Dispare `enroll()` e `authenticate()` com a tela em `RESUMED`, por
exemplo em resposta a um clique. O estado do aparelho pode ser consultado por
`getStatus()` sem abrir o prompt.

O módulo usa os contratos dos estruturantes e o `Logger` injetado. Os bindings de
biometria, permissões, estado do app e log pertencem aos respectivos módulos `di`,
carregados na inicialização do aplicativo. O módulo principal de design system
consome Koin, mas não declara registros nem depende de `designsystem/di`.

Para criptografia, armazenamento e recuperação dos valores, consulte os READMEs de
[biometria](../biometric/README.md) e [local storage](../local-storage/README.md).
