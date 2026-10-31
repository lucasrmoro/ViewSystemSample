# Local storage

Este estruturante mantém o armazenamento local compartilhado: preferências e
banco Room. Os consumidores dependem de `local-storage/api`; o `app` inclui
`local-storage/di` e inicializa `LocalStorageModule` antes de usar os contratos.
O módulo `di` depende de `api`, `impl`, dos helpers de dispatchers do `core` e dos
DAOs das features. Os consumidores não dependem das implementações nem de `di`.

## Preferências

`PreferencesDataSource` é singleton no Koin. Todas as preferências usam um único
DataStore criptografado com `datastore-tink`, inclusive valores gravados pelos
métodos comuns `putString`, `putBoolean`, `putInt` e `putLong`. Não existe opção de
armazenamento em texto simples. Os métodos são suspensos; falhas de IO propagam
para o chamador. `putString(key, null)` remove a entrada.

A factory mantém a instância do DataStore em uma propriedade `lazy`. O data source
acessa essa propriedade no dispatcher de IO injetado pela DI, garantindo que a
inicialização e os acessos não bloqueiem a thread principal.

## Preferências protegidas por biometria

`EncryptedPreferenceType` define chaves de campos com tipos nativos: `string`,
`boolean`, `int`, `long`, `float`, `double`, `stringSet` e `byteArray`. Use
`type.withValue(value)` para criar uma entrada compatível com o tipo da chave.
Não há serialização automática de objetos personalizados.

`putEncrypted(key, encryption, vararg entries)` recebe uma autorização de uso único
de `BiometricProvider.enroll()` e cifra todas as entradas em uma única operação.
O pacote inteiro é salvo em `key: String`, no mesmo DataStore Tink. Uma nova gravação
nessa chave substitui o pacote anterior; outras chaves permanecem intactas.
Chaves de campo duplicadas e lotes vazios são rejeitados antes da criptografia.

`getEncrypted(key)` retorna um snapshot cifrado ou `null` quando não há registro.
Passe `snapshot.data` para `BiometricProvider.authenticate()` e a autorização de
descriptografia retornada para `snapshot.decrypt()`. Essa operação não relê o
armazenamento: decifra exatamente o snapshot autorizado, mesmo que a chave tenha
sido atualizada enquanto o prompt estava aberto.

No sucesso, use `result.data.use { values -> ... }` e recupere cada campo com
`values[type]`. Os acessos são tipados e retornam `null` para campos ausentes.
Mantenha cada nome de campo associado ao mesmo tipo. O resultado pertence ao
chamador; o singleton não guarda valores descriptografados. Fechar o resultado
libera suas referências, mas não apaga valores já retidos pelo consumidor.

O `PreferencesFileSerializer` do próprio DataStore converte os tipos nativos em
bytes. Não há schema Protobuf próprio, KSerializer, JSON ou Parcelable. O pacote
biométrico contém versão, IV e ciphertext autenticado, persistidos em Base64.

A autorização criptográfica é consumida uma única vez. A operação retorna
`BiometricResult` para falhas de criptografia/descriptografia e invalidação da
chave. Erros de uso, como reutilizar a autorização ou acessar um resultado fechado,
lançam `IllegalStateException`. O fluxo de biometria, os estados do aparelho e os
erros disponíveis estão descritos no [README de biometria](../biometric/README.md).

## Room

O banco compartilhado fica em `impl`. `LocalStorageFactory` mantém uma única instância
do banco e da factory do DataStore; `di` registra o `PreferencesDataSource` como
singleton e os DAOs como factories. Entidades, DAOs e data sources
pertencem ao módulo `data` de cada feature. Esses módulos dependem apenas de
`local-storage/api`, evitando ciclos com a implementação do banco. Durante esta
fase de desenvolvimento, o esquema não é exportado e não são mantidas migrações.
