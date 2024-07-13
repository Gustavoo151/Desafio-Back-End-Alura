## FórumHub - API REST com Spring Boot e JWT

Bem-vindo ao FórumHub! Esta é uma API REST criada com Spring Boot para gerenciar tópicos de um fórum, com funcionalidades de autenticação e autorização utilizando JWT (JSON Web Token).

### Funcionalidades

- **CRUD de Tópicos**
    - Criar um novo tópico
    - Mostrar todos os tópicos
    - Mostrar um tópico específico
    - Atualizar um tópico
    - Deletar um tópico

- **Autenticação e Autorização**
    - Login com JWT
    - Proteção de rotas

### Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- H2 Database (ou outro banco de dados relacional de sua escolha)
- Maven

### Estrutura do Projeto

```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── forumhub/
│   │               ├── config/
│   │               │   └── SecurityConfig.java
│   │               ├── controller/
│   │               │   ├── AuthenticationController.java
│   │               │   └── TopicoController.java
│   │               ├── model/
│   │               │   ├── AuthenticationRequest.java
│   │               │   ├── AuthenticationResponse.java
│   │               │   ├── Topico.java
│   │               │   └── User.java
│   │               ├── repository/
│   │               │   ├── TopicoRepository.java
│   │               │   └── UserRepository.java
│   │               ├── security/
│   │               │   ├── JwtRequestFilter.java
│   │               │   └── JwtUtil.java
│   │               └── service/
│   │                   ├── TopicoService.java
│   │                   └── UserDetailsServiceImpl.java
│   └── resources/
│       ├── application.properties
│       └── templates/
│           └── login.html
└── test/
    └── java/
        └── com/
            └── example/
                └── forumhub/
                    └── ForumHubApplicationTests.java
```

### Configuração

#### Dependências

Adicione as seguintes dependências ao seu `pom.xml`:

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### Configuração do Banco de Dados

No arquivo `application.properties`, configure a conexão com o banco de dados. Por exemplo, para usar o H2 Database:

```properties
spring.datasource.url=jdbc:h2:mem:forumhub
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

### Executando a Aplicação

1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/forumhub.git
   ```
2. Navegue até o diretório do projeto:
   ```sh
   cd forumhub
   ```
3. Compile e execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

### Endpoints da API

- **Autenticação**
    - `POST /authenticate`: Autenticar um usuário e obter o token JWT.

- **Tópicos**
    - `GET /topicos`: Listar todos os tópicos (requer autenticação).
    - `GET /topicos/{id}`: Obter detalhes de um tópico específico (requer autenticação).
    - `POST /topicos`: Criar um novo tópico (requer autenticação).
    - `PUT /topicos/{id}`: Atualizar um tópico existente (requer autenticação).
    - `DELETE /topicos/{id}`: Deletar um tópico (requer autenticação).

### Exemplo de Requisição de Autenticação

Faça uma requisição `POST` para `/authenticate` com um JSON contendo `username` e `password`:

```json
{
  "username": "user",
  "password": "password"
}
```

O endpoint retornará um token JWT que deve ser usado para autenticar as requisições subsequentes. Adicione o token no cabeçalho `Authorization` das suas requisições:

```
Authorization: Bearer <seu-token-jwt>
```

### Segurança

As rotas de tópicos estão protegidas por JWT. É necessário autenticar-se para obter um token JWT e incluí-lo nas requisições subsequentes.

### Contribuindo

Se você deseja contribuir para este projeto, sinta-se à vontade para abrir issues ou enviar pull requests.
