# Diretrizes do repositório

Projeto Android modular que usa Kotlin, layouts XML, View Binding e injeção de dependências em tempo de execução.

## Arquitetura e módulos

- `app/` inicia o aplicativo. Mantenha todas as chamadas estruturais de inicialização e o carregamento dos módulos de DI explicitamente em `AppApplication`; factories e registros de DI não devem iniciar tarefas em segundo plano nem inicializar SDKs implicitamente.
- `features/<feature>/` contém os módulos de apresentação, domínio, dados, analytics e DI necessários. Cada feature tem seu próprio módulo `analytics`. Os contratos de repositório ficam em `domain/api`; implementações e fontes de dados ficam em um único módulo `data`. O domínio não depende de persistência nem de SDKs de fornecedores.
- Cada feature tem um único módulo `di`, sem divisão entre `api` e `impl`. Declare os bindings exclusivamente nele; a apresentação apenas consome dependências. Contratos de navegação entre features ficam em `structural/navigation/api`; o DI de cada feature registra adaptadores de entrada no registro compartilhado. Mantenha implementações de entrada, Activities, Fragments e grafos XML de navegação dentro da apresentação da feature, sem módulos Gradle de navegação por feature.
- `structural/<name>/` contém serviços compartilhados com módulos `api`, `impl` e `di`. `core` e `design-system` mantêm seus módulos principais ao lado de `di`.
- Use Gradle `implementation` por padrão; use `api` somente para contratos que devam ser reexportados intencionalmente. Mantenha callbacks de UI e modelos de adaptadores em Views/Fragments, sem expô-los por supertipos ou estado de ViewModel.
- Mantenha todas as dependências e tipos de fornecedores de DI dentro de `structural/di/impl`. Consumidores usam os contratos de `di/api` para registro, qualificadores, parâmetros e resolução. O serviço fundamental `di` tem apenas `api` e `impl`, sem um módulo próprio de autorregistro. Contratos de ViewModel do AndroidX e helpers de delegação ficam em `di/api`; suas implementações ficam em `di/impl`. O design-system depende apenas da API, nunca da implementação.
- As camadas das features dependem de contratos: domínio depende das APIs de domínio, e apresentação depende das APIs de domínio, não de dados nem de implementações de domínio. Serviços estruturais compartilhados são consumidos por suas APIs. O app é a raiz de composição e pode acessar todas as camadas; módulos DI estruturais conectam suas próprias implementações. Mantenha implementações internas e evite dependências circulares. Nem `api` nem `impl` dependem de `di`.
- APIs estruturais expõem suas próprias interfaces e modelos, preferencialmente em Kotlin e com coroutines. Represente resultados e atualizações assíncronas públicas com `Flow`, em vez de parâmetros de callback; mantenha callbacks de plataforma dentro de `impl`. Encapsule tipos de plataforma e SDKs em `impl`; adaptadores do ciclo de vida da tela pertencem a `design-system`.
- Antes de usar diretamente uma API de plataforma ou biblioteca de terceiros, verifique se um serviço estrutural existente é responsável por ela. Consuma o contrato da API; se faltar uma capacidade necessária, amplie o serviço estrutural em vez de duplicar o comportamento.
- Declare dependências opcionais somente nos consumidores que precisam delas; não as imponha a classes-base. Nunca mantenha uma Activity em um singleton. Injete dispatchers nos componentes que executam trabalho assíncrono.
- Nunca coloque clientes de bibliotecas, SDKs de fornecedores ou adaptadores de integração em `core`. Crie um serviço estrutural dedicado com `api`, `impl` e `di` sempre que uma integração desse tipo for necessária; mantenha os detalhes da biblioteca em `impl`.
- `build-logic/` contém os plugins de convenção; versões e coordenadas de dependências pertencem a `gradle/libs.versions.toml`.

## Documentação

- Escreva a documentação do projeto em inglês, incluindo READMEs, KDoc e documentos de arquitetura. Este `AGENTS.md` é mantido em pt-BR.
- Cada serviço estrutural tem um `README.md` na raiz que descreve sua responsabilidade, DI, uso e comportamentos relevantes. Detalhes de implementação dos serviços pertencem a esses READMEs, não a este arquivo.
- Interfaces estruturais públicas exigem KDoc. Documente comportamentos e condições que não sejam evidentes pela assinatura; não repita nomes de constantes nem descrições de getters e setters simples.
- Features, incluindo seus módulos `di`, não exigem KDoc. Atualize a documentação afetada junto com as mudanças de código.

## Código e organização

- Use quatro espaços e a formatação do Android Studio, seguindo o código existente.
- Cada módulo DI estrutural declara seus registros em uma propriedade privada `<service>Module = dependencyModule { ... }` dentro do objeto do módulo. O método `init()` usa uma chamada de linha única `loadModules(serviceModule)`. Chamadas que carregam vários módulos de feature mantêm um argumento por linha.
- Use PascalCase para tipos, camelCase para membros, SNAKE_CASE para constantes e snake_case para recursos Android. Preserve sufixos como `ViewModel`, `Repository`, `UseCase` e `Impl`; use `DTO` para objetos de transferência e `Entity` para entidades no pacote `entity`.
- Deixe uma linha em branco no início e no fim dos corpos de classes e interfaces; separe membros com linhas em branco. Enums não têm linhas em branco junto às chaves.
- Prefira nomes claros a comentários que apenas descrevam o código. Nomeie exceções não utilizadas como `_`.
- Não crie módulos Gradle nem diretórios vazios. Isso inclui diretórios chamados `api`, `impl` ou `di`: crie-os apenas quando tiverem conteúdo necessário e remova-os se ficarem vazios.
- Adicione um `.gitignore` a cada novo módulo Gradle.
- Todo campo de DTOs HTTP de requisição e resposta, inclusive DTOs aninhados, deve declarar `@JsonName("server_field_name")` de `network/api`. Nunca deduza nomes do protocolo a partir dos nomes das propriedades Kotlin; os mapeamentos precisam continuar válidos após ofuscação.
- Fontes Kotlin ficam em `src/main/kotlin/` e `src/<sourceSet>/kotlin/` para outras variantes. Recursos ficam em `src/main/res/`; testes Kotlin locais ficam em `src/test/kotlin/`.
- Ao mover ou excluir arquivos, remova diretórios que ficarem vazios, incluindo ancestrais vazios.

## Build e validação

Use o Gradle Wrapper com JDK 17 e Android SDK 36. Configure o SDK pelo Android Studio ou por `local.properties`. Os product flavors são `dev` e `prd`.

- `./gradlew :app:assembleDevDebug`: compila o APK.
- `./gradlew :app:installDevDebug`: instala em um dispositivo ou emulador.
- `./gradlew :app:testDevDebugUnitTest`: executa os testes locais do app.
- `./gradlew :app:lintDevDebug`: executa o Android Lint.

Execute verificações proporcionais à mudança e aos testes existentes nos módulos afetados. Use JUnit 4, o sufixo `Test` nas classes e nomes descritivos para os métodos. Não crie testes instrumentados. Nesta etapa, não adicione novos testes unitários, a menos que o usuário solicite.

## Commits e pull requests

- Crie commits somente quando solicitado. Revise todas as mudanças pendentes, inclusive arquivos novos, e separe objetivos independentes em commits coerentes.
- Use mensagens curtas em inglês no formato `type: description`, sem escopo. Exemplos: `feat: add analytics provider` e `refactor: simplify dependency injection`.
- Use tipos semânticos como `feat`, `fix`, `refactor`, `docs`, `test`, `build`, `ci`, `perf`, `style` e `chore`.
- Para mudanças incompatíveis, adicione `!` após o tipo e um rodapé `BREAKING CHANGE:`.
- Em pull requests, descreva o comportamento, os módulos afetados e a validação; vincule issues e inclua capturas de tela para mudanças visuais.
