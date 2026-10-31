# Biometria e armazenamento tipado

O estruturante de biometria prepara o Cipher e solicita autorização biométrica.
O local storage recebe uma operação autorizada, serializa as preferências nativas,
criptografa e persiste o resultado. O Cipher nativo fica em `biometric/impl`.
As APIs expõem interfaces Kotlin, sem tipos de plataforma.

`BiometricProvider` oferece:

- `getStatus()`: consulta a disponibilidade da biometria forte no aparelho.
- `enroll(prompt)`: retorna `BiometricEncryption`, autorizada para criptografar
  um único pacote de preferências. Não recebe nem persiste os valores.
- `authenticate(data, prompt)`: usa o IV do dado salvo e retorna
  `BiometricDecryption`, autorizada para descriptografar aquele dado.

`PreferencesDataSource` oferece:

- `putEncrypted(key, encryption, vararg entries)`: serializa as entradas,
  criptografa o lote uma vez e grava o pacote em `key: String`.
- `getEncrypted(key)`: lê o pacote uma vez e retorna `EncryptedPreferences`,
  um snapshot cifrado. Retorna `null` quando a chave não existe.

Após `authenticate(stored.data, prompt)`,
chame `stored.decrypt(decryption)`. Esse método descriptografa o snapshot sem reler
o DataStore e devolve `DecryptedPreferences`. Os acessos `values[preference]`
recuperam os valores tipados sem reutilizar Cipher.

## Preferências nativas tipadas

`EncryptedPreferenceType` oferece factories para os tipos nativos de preferences:
`string`, `boolean`, `int`, `long`, `float`, `double`, `stringSet` e `byteArray`.
Por exemplo, `EncryptedPreferenceType.string("sample_text")` define uma preferência
de texto. `preference.withValue(value)` vincula o valor ao tipo da preferência;
um tipo incompatível é rejeitado pelo compilador.

`key: String` identifica o pacote cifrado no DataStore. A leitura não precisa de serializadores nem de uma lista de campos: o pacote do DataStore
já contém os tipos nativos. Uma entrada ausente retorna `null`. Mantenha cada chave
associada ao mesmo tipo, como nas preferências comuns. Objetos personalizados não
são serializados automaticamente por essa API.

Cada chamada de `putEncrypted()` substitui o pacote inteiro daquela chave após
criptografar com sucesso. Para guardar outro conjunto sem substituir o anterior,
use outra chave de armazenamento e outra autorização. Acrescentar um campo a um
pacote existente exige recuperar os valores anteriores e gravar o conjunto completo;
o módulo não mescla silenciosamente conteúdo cifrado.

## Armazenamento e ciclo de vida dos dados

O local storage monta `MutablePreferences` em memória e usa o
`PreferencesFileSerializer` do próprio DataStore para converter os valores em
bytes. A biometria criptografa esses bytes uma única vez. A leitura inverte esse
fluxo com o mesmo serializer. Não há outro DataStore nem escrita intermediária em
disco. Os tipos AndroidX ficam restritos a `impl`.

Não há modelos de Batch, plugin Kotlin serialization, KSerializer ou dependência
direta de kotlinx.serialization/Protobuf. O formato binário interno é administrado
pelo DataStore existente. Entradas com chaves duplicadas são rejeitadas antes da
criptografia.

O `PreferencesDataSource` é singleton no Koin. Ele não guarda valores
descriptografados. O snapshot cifrado pertence ao chamador e permite preparar o
Cipher com o IV correto. O resultado descriptografado também pertence ao chamador;
use `result.data.use { values -> ... }` para liberar as referências após o uso.
`close()` não garante apagar Strings ou objetos já retidos pelo consumidor.

`EncryptedPreferencesImpl` é uma classe privada interna de
`PreferencesDataSourceImpl`. Ela representa o snapshot devolvido por
`getEncrypted(key)` e usa o codec e o dispatcher de IO da implementação externa.

`PreferencesDataStoreFactory` expõe a propriedade `dataStore`, inicializada por
`lazy`, sem um método `create()` intermediário. Leituras e gravações do data source
acessam essa propriedade dentro de `withContext(ioDispatcher)`, mantendo inclusive
a primeira inicialização fora da thread principal. O dispatcher e o singleton são
configurados pela DI.

A camada biométrica cifra o pacote antes da gravação no mesmo DataStore Tink
criptografado existente. O pacote persistido em Base64 contém versão, IV e
ciphertext com tag de autenticação. A chave do Keystore é reutilizada enquanto
válida; cada criptografia gera um Cipher e IV próprios.

`Parcelable` e `Parcel.marshall()` não são usados para persistência. A documentação
do Android informa que esse formato é destinado a IPC e pode mudar entre versões,
tornando dados antigos ilegíveis.

## Autorização por operação

Cada `BiometricEncryption` ou `BiometricDecryption` pode ser consumida uma única
vez. Uma tentativa de reutilização lança `IllegalStateException`; solicite outra
autorização para outra operação. Todos os valores do lote são processados por uma
única chamada de Cipher, portanto uma autenticação biométrica basta para o lote.

A descriptografia usa exatamente o snapshot apresentado a `authenticate()`.
Atualizar a chave durante o prompt não altera aquele snapshot. Uma autorização
para outro pacote não pode ser usada para descriptografá-lo.

AES/GCM de 256 bits protege conteúdo e integridade. A chave permanece no Android
Keystore e exige biometria forte em cada uso. Os dados não são portáveis entre
instalações ou aparelhos. A implementação não registra bytes descriptografados
em logs. Falhas de IO do DataStore seguem o contrato existente de preferências e
são propagadas ao chamador.

## Estado e switch de ativação

| Estado | Significado |
| --- | --- |
| `AVAILABLE` | Biometria forte cadastrada e disponível. |
| `NOT_ENROLLED` | Nenhuma credencial cadastrada atende à biometria forte. |
| `NO_HARDWARE` | Não há hardware adequado para a biometria solicitada. |
| `HARDWARE_UNAVAILABLE` | Hardware temporariamente indisponível. |
| `SECURITY_UPDATE_REQUIRED` | Sensor indisponível até receber atualização de segurança. |
| `UNSUPPORTED` | Opções incompatíveis com a versão do Android. |
| `UNKNOWN` | O sistema não conseguiu determinar a disponibilidade. |

Use `AVAILABLE` para permitir ativar o switch e persista a ativação somente após
salvar os valores com sucesso. A preferência de ativação pertence à feature.
Permita desativá-la mesmo se o hardware ficar indisponível. Consulte o estado
novamente em `onResume`, especialmente ao voltar das configurações do aparelho.

O estado descreve a capacidade do aparelho; não verifica a chave, a preferência
nem o estado da tela e não garante que uma operação futura concluirá.

## Falhas retornadas

| Erro | Significado |
| --- | --- |
| `NOT_ENROLLED` | Nenhuma biometria forte está cadastrada. |
| `UNAVAILABLE` | Biometria indisponível por outro motivo; consulte `getStatus()`. |
| `BIOMETRIC_REACTIVATION_REQUIRED` | A chave foi invalidada permanentemente; autentique-se novamente e reative a biometria. |
| `KEY_NOT_FOUND` | Não há chave para descriptografar o dado salvo. |
| `LOCKED_OUT` | O sistema bloqueou a autenticação por excesso de tentativas. |
| `OPERATION_IN_PROGRESS` | Outra solicitação está em andamento ou sendo encerrada. |
| `ENCRYPTION_FAILED` | Falha ao preparar, serializar ou criptografar as preferências. |
| `DECRYPTION_FAILED` | Falha ao preparar, descriptografar ou reconstruir as preferências salvas. |
| `CANCELLED` | O usuário ou o sistema cancelou o prompt. |
| `AUTHENTICATION_FAILED` | O sistema encerrou a autenticação com outro erro ou não devolveu o Cipher autorizado. |

Uma chave invalidada é reportada ao chamador e nunca substituída automaticamente.
O erro informa a invalidação, não identifica unicamente a causa. A recuperação
das credenciais e a decisão de redefinir a chave pertencem ao fluxo da aplicação.

Configuração e ciclo de vida são pré-condições: `BiometricPromptConfig` rejeita
títulos ou textos de cancelamento vazios com `IllegalArgumentException`.
`enroll()` e `authenticate()` exigem a tela em `RESUMED`; chamadas fora desse
estado lançam `IllegalStateException`. Cancelar a coroutine cancela o prompt e
propaga `CancellationException`. Uma tentativa sem reconhecimento mantém o prompt
aberto para nova tentativa. O vínculo com sensor biométrico e Keystore precisa ser
validado em dispositivo com biometria forte cadastrada.

Referências: [biometria](https://developer.android.com/identity/sign-in/biometric-auth),
[Keystore](https://developer.android.com/privacy-and-security/keystore) e
[PreferencesFileSerializer](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/PreferencesFileSerializer)
e [Parcel](https://developer.android.com/reference/android/os/Parcel).
