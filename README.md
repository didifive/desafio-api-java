# DESAFIO API - Programa Start

## Description

Repositório para entrega do Desafio API (semana 18/07 ~ 29/07/2022) - Programa GFT Start  
Objetivo: Aplicar os conceitos de API aprendidos nas semanas de estudo, juntamente com a pesquisa de novos recursos;  
Escopo: Neste desafio você deverá desenvolver diversos End Points para um sistema de atendimento veterinário, nesse sistema, deverá considerar somente o atendimento de cachorros.
Entrega Básica:
O sistema deverá obrigatoriamente fornecer End Points para:

- CRUD completo para clientes (armazenar somente dados básicos)
- CRUD completo para médicos veterinários (armazenar somente dados básicos)
- CRUD completo para o cachorro (cada animal deverá estar associado a um tutor/cliente | dados básicos do animal)
- Registrar dados do atendimento: veterinário que está atendendo, tutor, animal em atendimento, data, hora, dados do animal no dia, diagnóstico,
comentários.
- Autenticação do usuário (Basic Authentication – user:password) para acessar os End Points.
- Não deixe de popular o banco

Desafios bônus (Exceeds):

- No cadastro do animal, recuperar dados da raça buscando na API fornecida pelo link no final da descrição da atividade.
- Fornecer ao menos 3 serviços da API descrita no final da atividade para o cliente ( o cliente não pode acessar a API diretamente, deve acessar seu End Point, e seu End Poit deverá acessar a API e passar os dados para o cliente)
- Manter histórico dos atendimentos e permitir que tanto o cliente quanto veterinário possam acessar esses dados.
    OBS: Nesse caso, o cliente poderá acessar somente dados das consultas de seus animais. Os veterinários por sua vez, podem ver histórico de qualquer consulta.
- Swagger ou projeto no postman
- TDD

O link da API que deverá ser utilizada é apresentado a seguir:
<https://thedogapi.com/>

### Meu AmigAU! - Versão 1.0.0

#### Clínica Veterinária Especializada

Para o desafio foi criado **Meu AmigAU! - Clínica Veterinária Especializada**.
Neste projeto foi aplicado:

- Os arquivos fontes dos diagramas EER e UML que aparecem como imagem neste documento estão disponíveis na pasta `docs/diagrams`. O diagrama EER foi feito utilizando o MySQL Workbench Model e o UML foi feito utilizando o site [Draw.io];
- Abstração criando `Person` para as classes `Client` e `Vet`. Assim é possível que uma pessoa seja cadastrada como cliente e veterinário sem necessitar colocar dados cadastrais pessoais, como o CPF/CNPJ, data de nascimento ou o endereço novamente, e pode ser aproveitada evoluções do projeto, como cadastro de funcionários;
- Criado a entidade `Address` para que a entidade `Person` possa ter mais de um endereço cadastrado;
- Criado o enumerador `AddressType` para diferenciar os tipos de endereços que são vinculados à pessoa;
- Criado o enumerador `PersonType` para que tenha a possibilidade de informar pessoa física ou jurídica. Este enumerador também auxilia na validação de CPF ou CNPJ na mesma variável, usando como apoio as interfaces `CpfGroup` e `CnpjGroup` e a classe `PersonGroupSequenceProvider`, conforme orientações da página [Validando CPF/CNPJ na mesma variável];
- O usuário, entidade `User`, pode possuir mais de um perfil de acesso;
- Criada a classe `Breed` para guardar dados da raça com campos baseados na página [TheDogAPI Postman Docs];
- Utilizado o Swagger v3 (OpenApi) e na segurança foi usado o bean `SecurityFilterChain` para não utilizar o `WebSecurityConfigurerAdapter` que foi depreciado nas versões recentes do spring;
- Foram criadas classes com constants (`public static final`), pacote `enums.constants`, para guardar textos usados para mapeamentos de rotas, para documentação no swagger e em mensagens do sistema, assim é possível reaproveitá-las nas classes do sistema e testes, além de organizar os textos para facilitar revisão ou mesmo tradução;
- Para evitar expor senhas ou chaves em repositório remoto, foram utilizadas variáveis de ambiente (_environment variables_) configuradas na IDE Ecplise, mais detalhes estarão na seção _Installation_;
- Criado o arquivo `application-test.properties` para que nos testes carregue o banco de dados H2, desativado o flyway e outros detalhes para evitar problemas com testes, dados da aplicação e problema com variáveis de ambientes em testes;
- Criado a classe `DateTimeConverter` no pacote `com.gft.meuamigau.utils` para fazer a conversão de datas para string e vice-versa e ser utilizada em método estáticos (`static`), ele utiliza `DateTimeFormatter` e `LocalDate`, pois o `SimpleDateFormatter`, que faria uma conversão mais direta de `Date` para `String`, e vice-versa, não é _thread-safe_ e, portanto, utilizá-lo em métodos estáticos pode acarretar em problemas, [ver mais sobre](https://stackoverflow.com/questions/6137548/can-we-declare-simpledateformat-objects-as-static-objects);
- Para nomes de variáveis diferentes entre objeto e JSON, foi utilizado as intruções em [Serialize POJO to JSON with different names using GSON];
- Para os testes, foi criado o método estático público `assertThrowsExceptionWithCorrectMessage` na classe `Assertions`, pacote `com.gft.meuamigau.utils`, para abstrair a conferência do lançamento de excessão com mensagem correta;
- Criado a classe `EntityDtoBox`, pacote `utils`, para poder fazer um mesmo método poder retornar entidade ou DTO. Esta classe foi utilizada em métodos create e findById dos services;
- Os endpoints do grupo "Raças de Cachorros" fazem busca na API externa [TheDogAPI]. Apenas o endpoint <http://localhost:8080/api/v1/breeds/{id>} que realiza primeiramente uma consulta na base de dados local e quando não localiza a raça cadastrada, ele procura na base externa e salva os dados;
- Todo cadastro de cachorro que inclui um id de raça que ainda não existe na base de dados local, já aproveita o método de busca no endpoint <http://localhost:8080/api/v1/breeds/{id>}, e salva as informações da raça localmente;
- No atendimento existe o atributo de cliente explícito, pois caso ocorrer a mudança de cliente no cachorro o atendimento ainda terá vinculo com o cadastro de cliente que era o responsável pelo cachorro no momento do atendimento.

## Configuration

O projeto foi feito utilizando:

- [IDE Eclipse] versão 2022-06 (4.24.0).
- Iniciado com Spring Starter Project, com as configurações e dependências:
  - Project: [Maven] Project
  - Language: Java
  - [Spring Boot] 2.7.1
  - Packaging: Jar
  - [Java 17]
  - Dependencies:
    - Spring Boot DevTools
    - Spring Web
    - Spring Data JPA
    - Spring Security
    - Validation
    - MySQL Driver
    - Flyway
    - Lombok
    - H2 Database
- Dependências adicionadas manualmente ao `pom.xml`:
  - `springdoc-openapi-ui` e `springdoc-openapi-security` para Swagger v3 (OpenApi);
  - `java-jwt` para trabalhar com token JWT;
  - `gson` para [Converter JSON para Objeto com GSON];
  - `jacoco-maven-plugin` para gerar relatório de coverage quando o teste é executado diretamente pelo terminal. _É necessário também inserir plugin específico, verifique o `pom.xml` para checar o plugin `jacoco-maven-plugin` adicionado_

## Visuals

Logotipo feito utilizando o site [Canva]:  
![Meu AmigAU! Logo](docs/images/meu_amigau_-_logo.png?raw=true "Meu AmigAU! Logo")  
Spring Banner personalizado com utilização de conversão de imagem para ascii pelo site [ASCII-Art]:  
![Spring Banner Personalizado](docs/images/spring_banner.png?raw=true "Spring Banner Personalizado")  
Diagrama EER com MySQL Workbench Model:  
![Diagrama EER MySQL Workbench Model](docs/images/diagrams/meu_amigau_eer_diagram.png?raw=true "Diagrama EER MySQL Workbench Model")  
Diagrama de classes UML feito utilizando o site [Draw.io]:  
![Diagrama UML - Entities](docs/images/diagrams/meu_amigau_uml-Entities.drawio.png?raw=true "Diagrama UML - Entities")  
![Diagrama UML - DTOs](docs/images/diagrams/meu_amigau_uml-DTOs.drawio.png?raw=true "Diagrama UML - DTOs")  
Variáveis de ambiente usadas no `application.properties`:  
![application.properties](docs/images/application.properties_environment_variables_useds.PNG?raw=true "application.properties")  
Swagger v3 - OpenApi:  
![Swagger](docs/images/swagger/meu_amigau_-_swagger.png?raw=true "Swagger")  
Swagger Schemas - Cadastro de Pessoas - No Schema do RecordPersonDTO é mostrado os campos para requisição, cada um sua descrição e marcado com * se é requerido, no caso de uso de enumerador, é mostrada as opções disponíveis para o campo
![Requisição de Cadastro de Pessoa](docs/images/cadastro-pessoa/cadastro_pessoa_requisicao_personType_enum.PNG?raw=true "Requisição de Cadastro de Pessoa")  
Resposta de cadastro de pessoa no Swagger, é mostrado também os outros tipos de respostas que também podem ocorrer, como o 409 que indica violação de integridade em algum campo  
![Resposta de Cadastro de Pessoa](docs/images/cadastro-pessoa/cadastro_pessoa_respostas.PNG?raw=true "Resposta de Cadastro de Pessoa")  
Resposta de cadastro de pessoa com violação de integridade ao tentar cadastrar nova pessoa com CPF ou CNPJ já existente:  
![Violação de Integridade - CPF ou CPNJ deve ser único](docs/images/cadastro-pessoa/cadastro_pessoa_validacao_unico_cpf_cnpj.PNG?raw=true "Violação de Integridade - CPF ou CPNJ deve ser único")  
Resposta de cadastro de pessoa com violação de integridade ao tentar cadastrar nova pessoa com Email já existente:  
![Violação de Integridade - CPF ou CPNJ deve ser único](docs/images/cadastro-pessoa/cadastro_pessoa_validacao_unico_email.PNG?raw=true "Violação de Integridade - CPF ou CPNJ deve ser único")  
Classificação de Endpoints no Swagger - Tags por assunto:  
![Swagger Tags 01](docs/images/swagger/meu_amigau_-_swagger_-_endpoints-tag01.png?raw=true "Swagger Tags 01")  
Classificação de Endpoints no Swagger - Tags por autorização de acesso:  
![Swagger Tags 02](docs/images/swagger/meu_amigau_-_swagger_-_endpoints-tag02.png?raw=true "Swagger Tags 02")  
Classificação de Endpoints no Swagger - Tags por método HTTP:  
![Swagger Tags 03](docs/images/swagger/meu_amigau_-_swagger_-_endpoints-tag03.png?raw=true "Swagger Tags 03")  
Resultado dos testes na IDE Eclipse:  
![Resultado de testes pelo terminal](docs/images/eclipse_tests.PNG?raw=true "Resultado de testes pelo terminal")  
Resultado dos testes pelo terminal:  
![Resultado de testes pelo terminal](docs/images/terminal_tests.PNG?raw=true "Resultado de testes pelo terminal")  
Relatório de coverage gerado pelo jacoco plugin:  
![Relatório coverage jacoco plugin](docs/images/jacoco_coverage_report.PNG?raw=true "Relatório coverage jacoco plugin")  

## Installation

Para abrir o projeto basta clonar o repositório ou realizar o download e após:

- Importar o projeto, preferencialmente na IDE Eclipse;
- Fazer atualizações das dependências do Maven (Alt+F5 no Eclipse);
- Possuir no mínimo JDK 17 LTS instalado, sugestão de JDK: [OpenJDK Zulu];
- Possuir acesso à internet para atualização de dependências e acesso à API de terceiros;
- Para projeto carregar corretamente na IDE pode ser necessário [Instalar Lombok];
- Necessário possuir o banco de dados MySQL Server, caso não possua, pode visitar [MySQL Community Download] para download e instalação;
- No arquivo src/main/resources/application.properties verificar e se necessário alterar os parâmentros conforme segue:
  - URL de conexão com o banco: `spring.datasource.url=jdbc:mysql://localhost:3306/meu-amigau?createDatabaseIfNotExist=True`
  - Usuário root do banco: `spring.datasource.username=root` - O usuário root padrão com permissão ao MySQL Server local, alterar somente caso seja esteja desativado ou seja diferente;
  - Usuário root do banco: `spring.datasource.password=${DATABASE_ROOT_PASSWORD}` - Senha do banco de dados, alterar para a senha do root utilizado localmente ou setar valor na variável de ambiente `DATABASE_ROOT_PASSWORD`;
  - Chave secreta para o JWT gerar e validar tokens: `meu-amigau-api.jwt.secret=${TOKEN_API_SECRET}` - Alterar para chave secreta de sua preferencia ou setar valor na variável de ambiente `TOKEN_API_SECRET`.
  - Chave The Dog Api: `meu-amigau-api.the-dog-api.x-api-key=${THE_DOG_API_X_API_KEY}` - Alterar para chave gerada no site [TheDogAPI] ou setar o valor da chave na variável de ambiente `THE_DOG_API_X_API_KEY`.
- Variáveis de ambientes que devem ser setadas no sistema ou na IDE para funcionamento adequado da aplicação:
  - DATABASE_ROOT_PASSWORD
  - TOKEN_API_SECRET
  - THE_DOG_API_X_API_KEY
- Setar valor de variável de ambiente na [IDE Eclipse]:
  - Menu Run -> Run Configurations..., selecionar o app desejado, no caso `meu-amigau-api`, e procurar a aba `Environment`  
    ![Environment Configuration Eclipse 1](docs/images/environment-configuration/environment_configuration_eclipse_01.PNG?raw=true "Environment Configuration Eclipse 1")  
  - Após, basta ir no botão `Add...` para adicionar a variável e valor, exemplo:
    ![Environment Configuration Eclipse 2](docs/images/environment-configuration/environment_configuration_eclipse_02.PNG?raw=true "Environment Configuration Eclipse 2")  
  - Adicionalmente, pode colocar a execução da aplicação no favoritos da IDE, basta ir na aba `Common`  
    ![Environment Configuration Eclipse 3](docs/images/environment-configuration/environment_configuration_eclipse_03.PNG?raw=true "Environment Configuration Eclipse 3")  

## Tests

Os testes foram feitos utilizando o [JUnit 5], Mockito e MockMVC.  

- Utilizando a [IDE Eclipse], basta executar (Run As) a pasta de teste ou escolher o arquivo.
- Utilizando o terminal (PowerShell ou similiar), basta executar na pasta do projeto o comando abaixo:

    ```shell
    ./mvnw clean test
    ```

    _Após o teste finalizado com sucesso, é possível verificar relatório de coverage em: target/site/jacoco/index.html_  

_Observação: Até a versão 1.0.0 existe teste na classe `BreedServiceTest`, pacote `services`, que faz conexão ao [TheDogAPI], portanto para o teste executar é necessário estar online._

## Usage

Antes de iniciar o projeto, configurar as variáveis de ambiente `DATABASE_ROOT_PASSWORD`, `TOKEN_API_SECRET` e `THE_DOG_API_X_API_KEY` no sistema ou IDE, ou então editar o arquivo `application.properties` nas condfigurações `spring.datasource.password`, `meu-amigau-api.jwt.secret` e `meu-amigau-api.the-dog-api.x-api-key` com suas informações.
Para iniciar o projeto, basta utilizar uma das opções abaixo:

- Utilizando a [IDE Eclipse], basta executar (Run) a classe `MeuAmigauApiApplication` no pacote `com.gft.meuamigau`.
- Utilizando o terminal (PowerShell ou similiar), basta executar na pasta do projeto o comando abaixo:

    ```shell
    ./mvnw clean package spring-boot:run
    ```

Ao executar a aplicação será criado o esquema do banco de dados através do flyway e o banco será automaticamente populado através da classe `PopulateDB`, pacote `config.populate`, com:

- 5 perfis conforme disponíveis pela classe `RoleName.class` do pacote `enums`:
  - ROLE_ADMIN
  - ROLE_USER
  - ROLE_PERSON
  - ROLE_VET
  - ROLE_CLIENT
- 2 usuários:
  - username: admin@email.com
    - senha: pass@1234
    - perfil(s): ROLE_USUARIO, ROLE_ADMIN
  - username: usuario@email.com
    - senha: pass@1234
    - perfil(s): ROLE_USUARIO
- 4 pessoas
- 3 clientes
- 2 veterinários
- 1 raça de cachorro (_Undefined Race_)
- 4 cachorros
- 5 atendimentos

## Endpoints

- Autenticação:
    | Método | URL                               | Perfil(s) Autorizado(s) |
    | ------ | ---                               | ----------------------- |
    | GET    | <http://localhost:8080/api/v1/auth> | Público                 |

- Usuários:
    | Método | URL                                                | Perfil(s) Autorizado(s)                   |
    | ------ | ---                                                | -----------------------                   |
    | POST   | <http://localhost:8080/api/v1/users>                 | ROLE_ADMIN                                |
    | GET    | <http://localhost:8080/api/v1/users>                 | ROLE_ADMIN                                |
    | GET    | <http://localhost:8080/api/v1/users/{id>}            | _ONLY OWN USER_, ROLE_ADMIN               |
    | PUT    | <http://localhost:8080/api/v1/users/{id>}            | ROLE_ADMIN                                |
    | PATCH  | <http://localhost:8080/api/v1/users/pass/{username>} | _ONLY OWN USER_                           |
    | DELETE | <http://localhost:8080/api/v1/users/{id>}            | ROLE_ADMIN {NOT OWN USER}**_              |

- Pessoas:
    | Método | URL                                                                      | Perfil(s) Autorizad(s)                                |
    | ------ | ---                                                                      | -----------------------                               |
    | POST   | <http://localhost:8080/api/v1/people>                                      | ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/people>                                      | ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/people/{id>}                                 | _ROLE_PERSON {ONLY OWN USER}*_, ROLE_USER, ROLE_ADMIN |
    | PUT    | <http://localhost:8080/api/v1/people/{id>}                                 | ROLE_ADMIN                                            |
    | PATCH  | <http://localhost:8080/api/v1/people/{id}/handle-user/id/{newUserId>}      | ROLE_ADMIN                                            |
    | PATCH  | <http://localhost:8080/api/v1/people/{id}/handle-user/username/{username>} | ROLE_ADMIN                                            |
    | DELETE | <http://localhost:8080/api/v1/people/{id>}                                 | ROLE_ADMIN                                            |

- Clientes:
    | Método | URL                                       | Perfil(s) Autorizado(s)                               |
    | ------ | ---                                       | -----------------------                               |
    | POST   | <http://localhost:8080/api/v1/clients>      | ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/clients>      | ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/clients/{id}> | _ROLE_CLIENT {ONLY OWN USER}*_, ROLE_USER, ROLE_ADMIN |
    | PUT    | <http://localhost:8080/api/v1/clients/{id}> | ROLE_USER, ROLE_ADMIN                                 |
    | DELETE | <http://localhost:8080/api/v1/clients/{id}> | ROLE_ADMIN                                            |

- Veterinários:
    | Método | URL                                    | Perfil(s) Autorizado(s)                            |
    | ------ | ---                                    | -----------------------                            |
    | POST   | <http://localhost:8080/api/v1/vets>      | ROLE_USER, ROLE_ADMIN                              |
    | GET    | <http://localhost:8080/api/v1/vets>      | ROLE_USER, ROLE_ADMIN                              |
    | GET    | <http://localhost:8080/api/v1/vets/{id}> | _ROLE_VET {ONLY OWN USER}*_, ROLE_USER, ROLE_ADMIN |
    | PUT    | <http://localhost:8080/api/v1/vets/{id}> | ROLE_USER, ROLE_ADMIN                              |
    | DELETE | <http://localhost:8080/api/v1/vets/{id}> | ROLE_ADMIN                                         |

- Raças de Cachorros:
    | Método | URL                                             | Perfil(s) Autorizado(s)         |
    | ------ | ---                                             | -----------------------         |
    | GET    | <http://localhost:8080/api/v1/breeds>             | ROLE_VET, ROLE_USER, ROLE_ADMIN |
    | GET    | <http://localhost:8080/api/v1/breeds/name/{name}> | ROLE_VET, ROLE_USER, ROLE_ADMIN |
    | GET    | <http://localhost:8080/api/v1/breeds/{id}>        | ROLE_VET, ROLE_USER, ROLE_ADMIN |
    | GET    | <http://localhost:8080/api/v1/breeds/images/{id}> | ROLE_VET, ROLE_USER, ROLE_ADMIN |

- Cachorros:
    | Método | URL                                            | Perfil(s) Autorizado(s)                                         |
    | ------ | ---                                            | -----------------------                                         |
    | POST   | <http://localhost:8080/api/v1/dogs>              | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/dogs>              | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/dogs/clients/{id}> | _ROLE_CLIENT {ONLY OWN USER}*_, ROLE_VET, ROLE_USER, ROLE_ADMIN |
    | GET    | <http://localhost:8080/api/v1/dogs/{id}>         | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | PUT    | <http://localhost:8080/api/v1/dogs/{id}>         | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | DELETE | <http://localhost:8080/api/v1/dogs/{id}>         | ROLE_ADMIN                                                      |

- Atendimentos:
    | Método | URL                                                   | Perfil(s) Autorizado(s)                                         |
    | ------ | ---                                                   | -----------------------                                         |
    | POST   | <http://localhost:8080/api/v1/attendances>              | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/attendances>              | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/attendances/clients/{id}> | _ROLE_CLIENT {ONLY OWN USER}*_, ROLE_VET, ROLE_USER, ROLE_ADMIN |
    | GET    | <http://localhost:8080/api/v1/attendances/dogs/{id}>    | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/attendances/vets/{id}>    | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | GET    | <http://localhost:8080/api/v1/attendances/{id}>         | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | PUT    | <http://localhost:8080/api/v1/attendances/{id}>         | ROLE_VET, ROLE_USER, ROLE_ADMIN                                 |
    | DELETE | <http://localhost:8080/api/v1/attendances/{id}>         | ROLE_ADMIN                                                      |

**IMPORTANTE:** Como é uma aplicação que possui checagem de permissão de acesso, é necessário que se utilize token para as requisições através do Endpoint, o(s) perfil(s) de acesso(s) estão relacionados nas tabelas acima.  
_OBSERVAÇÃO:_ Os perfis marcados com _{ONLY OWN USER}*_, indica que a permissão é atribuída somente para que o usuário do perfil possa acessar apenas o recurso que tenha relação direta para si.  
_OBSERVAÇÃO 2:_ No endpoint de delete o ROLE_ADMIN está marcado com _{NOT OWN USER}**_, indicando que não é possível executar o processo sendo o próprio usuário do recurso, no caso, um usuário mesmo sendo administrador, não pode se deletar.
_OBSERVAÇÃO 3:_ No endpoint que permite ao próprio usuário trocar a senha a permissão está marcada com: _ONLY OWN USER_

A documentação completa dos Endpoints estará disponível através do Swagger que estará acessível pelo link quando o projeto iniciar: <http://localhost:8080/swagger-ui.html>

Também existe arquivo de Coleção do Postman, basta importar o arquivo `MeuAmigAU-API.postman_collection.json` que está na pasta docs.

### Autenticando e utilizando o token no Swagger

Para fazer a autenticação e utilizar o token no Swagger siga os passos:  
1- Na página do Swagger (<http://localhost:8080/swagger-ui.html>) procure por "1. Autenticação", depois o endpoint POST em "/api/v1/auth Generate Token" e clique em "Try it out"  
![Swagger Authentication Step 01](docs/images/swagger/swagger_auth01.png?raw=true "Swagger Authentication Step 01")  
2- Irá ter um campo de texto (textarea) com um JSON de modelo, altere o email e password para email e senha de usuário já cadastrado (exemplo: admin@email.com), depois é só clicar em "Execute"  
![Swagger Authentication Step 02](docs/images/swagger/swagger_auth02.png?raw=true "Swagger Authentication Step 02")  
3- Após a execução irá aparecer a resposta abaixo na seção "Server response", o token estará no "Response body", copiar somente o token sem as aspas. _Se retornar erro 401, o usuário ou senha estão inválidos._  
![Swagger Authentication Step 03](docs/images/swagger/swagger_auth03.png?raw=true "Swagger Authentication Step 03")  
4- Para realizar a autorização de acesso aos endpoist basta clicar no botão "Authorize" ou no ícone de cadeado que aparece em frente aos endpoints que exigem autorização
![Swagger Authentication Step 04](docs/images/swagger/swagger_auth04.png?raw=true "Swagger Authentication Step 04")  
5- Na janela que abrir, informar o token gerado e copiado no passo 3 e clique em "Authorize". _A autorização disponível já está configurada para aceitar Bearer token, portanto é só colar o token sem acrescentar nada_  
![Swagger Authentication Step 05](docs/images/swagger/swagger_auth05.png?raw=true "Swagger Authentication Step 05")  
6- Após informar o token, a janela irá mudar com a mensagem de "Authorized" e os ícones de cadeados que aparecem no botão "Authorize" e nos endpoints que exigem autorização irão aparecer como cadeado fechado  
![Swagger Authentication Step 06](docs/images/swagger/swagger_auth06.png?raw=true "Swagger Authentication Step 06")
![Swagger Authentication Step 06](docs/images/swagger/swagger_auth07.png?raw=true "Swagger Authentication Step 06")  

### Autenticando e utilizando o token no Postman

Para fazer a autenticação e utilizar o token no Postman siga os passos:  
1- No aplicativo Postman é necessário fazer a requisição com método POST na URL <http://localhost:8080/api/v1/auth> com o nome de usuário e senha no body da requisição. A configuração da requisição está na Coleção "MeuAmigAU! API / Autenticação / Gerar Token de Acesso"  
![Postman Authentication Step 01](docs/images/postman/postman_auth01.png?raw=true "Postman Authentication Step 01")  
2- A requisição bem sucedida irá retornar resposta 200 OK e o token, copie o token  
![Postman Authentication Step 02](docs/images/postman/postman_auth02.png?raw=true "Postman Authentication Step 02")  
3- Após o token gerado e copiado, é só ir em qualquer requisição, ir na aba Authorization, escolher a opção "Bearer Token" e colar o token.  
![Postman Authentication Step 03](docs/images/postman/postman_auth03.png?raw=true "Postman Authentication Step 03")  

## Roadmap

Aqui estão listadas sugestões para futuras atualizações:

- Criar campos para guardar _timestamps_ de criação e atualização com o usuário que realizou a operação;
- CRUD para raças de cachorros (Breeds);
- Transformar o delete definitivo em um delete lógico, quando a informação continua presente nos dados, mas fica "inativa" no sistema;
- Para o processo acima funcionar corretamente, deve-se considerar como serão tratados novos cadastros que por ventura violem a restrição de ser único para a entidade/tabela, como email ou documento;
- Criar endpoint no cachorro para poder permitir a troca de cliente;
- Criar endpoint para permitir pesquisar atendimentos por data e hora;
- Para o caso de permitir troca de cliente, criar DTO para atualização de atendimento deixando o campo de cliente disponível para preenchimento, atualmente o cliente é preenchido automaticamente pelo sistema;
- Criar testes unitários com contexto de segurança para testar a acessibilidade dos perfils, assim pode cobrir 100% o `AttendanceService`;
- Criar testes que cubram 100% o `BreedService` em relação à conexão da API externa e sem a necessidade da resposta real. Verificar a implementação de Mock Server para não necessitar de conectar ao recurso real.

## Support and Contributing

Dúvidas, problemas ou sugestões: abrir Issue ou Merge Request.

## Authors and acknowledgment

Desafio proposto por Michel, Ubiratran, Clécio e equipe do Programa Start da GFT.  
Feito por [Luis Carlos Zancanela]  

> O código deve ser elegante para agradar a quem olhar.  

> ###### _ZANCANELA L. C., 2022. Desafio API (semana 18/07 ~ 29/07/2022) - Programa GFT Start._  

## Project status

_Done in 29/07/2022_  

[Java 17]: https://docs.oracle.com/en/java/javase/17/docs/api/index.html
[JUnit 5]: https://junit.org/junit5/docs/current/user-guide/index.html
[IDE Eclipse]: https://www.eclipse.org/ide/
[Spring Boot]: https://spring.io/projects/spring-boot
[Maven]: https://maven.apache.org/
[MySQL Community Download]: https://dev.mysql.com/downloads/
[OpenJDK Zulu]: https://www.azul.com/downloads/
[Draw.io]: https://app.diagrams.net/
[Canva]: https://www.canva.com/pt_br/
[ASCII-Art]: https://ascii-art.botecodigital.dev.br/

[Luis Carlos Zancanela]: https://github.com/didifive

[Instalar Lombok]: https://dev.to/gilton/instalando-o-lombok-no-eclipse-3c59#:~:text=%20Para%20instalar%20o%20Lombok%20no%20Eclipse%20e,clique%20duas%20vezes%20no%20%27lombok.jar%27%20%28double-click%29%3B%20More%20

[Validando CPF/CNPJ na mesma variável]: https://medium.com/blog-gilson-silva-ti/validando-cpf-cnpj-na-mesma-vari%C3%A1vel-com-bean-validation-4429a49e9bb5

[TheDogAPI]: https://thedogapi.com/
[TheDogAPI Postman Docs]: https://documenter.getpostman.com/view/4016432/the-dog-api/RW81vZ4Z#26bd3f92-dd58-4569-bc13-22fa76396fe8

[Converter JSON para Objeto com GSON]: https://stackoverflow.com/questions/1688099/converting-json-data-to-java-object
[Serialize POJO to JSON with different names using GSON]: https://stackoverflow.com/questions/32547662/serialize-pojo-to-json-with-different-names-using-gson
