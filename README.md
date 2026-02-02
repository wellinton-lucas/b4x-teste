
# b4x Teste

Teste técnico para a empresa b4x.

## Sobre O Projeto

Este projeto é uma API REST desenvolvida para gerenciar produtos, seguindo todas as boas práticas e princípios REST. A aplicação implementa um sistema completo de CRUD (Create, Read, Update, Delete) com foco em segurança e usabilidade.

### Principais Funcionalidades

- Gerenciamento Completo de Produtos: Criação, listagem, atualização (completa e parcial) e exclusão de produtos
- Sistema de Autenticação e Autorização: Implementação de segurança baseada em JWT com diferentes níveis de permissão
- Rastreabilidade de Alterações: Todos os produtos mantêm registro de data da última modificação
- CORS Configurado: Permite integração segura com o frontend

Tecnologias Utilizadas

- Java 17
- Spring Boot 4.0.2
- Spring Security
- Spring Data JPA
- H2 Database
- JWT
- Maven
- Blaze Persistence

### Arquitetura e Padrões
O projeto segue os princípios REST e implementa:

- Endpoints semânticos e padronizados
- Uso correto dos verbos HTTP (GET, POST, PUT, PATCH, DELETE)
- Códigos de status HTTP apropriados
- Separação de responsabilidades (Controller, Service, Repository)
- DTOs para transferência de dados
- Tratamento centralizado de exceções

```
📦 
├─ .gitignore
├─ HELP.md
├─ README.md
├─ mvnw
├─ mvnw.cmd
├─ pom.xml
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ b4x
   │  │        └─ teste
   │  │           ├─ TesteApplication.java
   │  │           ├─ application
   │  │           │  ├─ dto
   │  │           │  │  ├─ DTOEmail.java
   │  │           │  │  ├─ LoginUserDto.java
   │  │           │  │  └─ RecoveryJwtTokenDto.java
   │  │           │  ├─ service
   │  │           │  │  ├─ ProdutoService.java
   │  │           │  │  └─ UsuarioService.java
   │  │           │  ├─ utils
   │  │           │  │  ├─ GeneratedUuidV7.java
   │  │           │  │  ├─ UuidUtils.java
   │  │           │  │  └─ UuidV7Generator.java
   │  │           │  └─ views
   │  │           │     ├─ ProdutoView.java
   │  │           │     └─ UsuarioView.java
   │  │           ├─ domain
   │  │           │  ├─ enums
   │  │           │  │  ├─ CATEGORIA.java
   │  │           │  │  └─ ROLES.java
   │  │           │  └─ model
   │  │           │     ├─ Produto.java
   │  │           │     └─ Usuario.java
   │  │           ├─ infrastructure
   │  │           │  ├─ config
   │  │           │  │  ├─ BlazePersistenceConfiguration.java
   │  │           │  │  ├─ GlobalExceptionHandler.java
   │  │           │  │  └─ WebConfig.java
   │  │           │  ├─ repository
   │  │           │  │  ├─ ProdutoRepository.java
   │  │           │  │  └─ UsuarioRepository.java
   │  │           │  └─ security
   │  │           │     ├─ JwtTokenService.java
   │  │           │     ├─ SecurityConfiguration.java
   │  │           │     ├─ UserAuthenticationFilter.java
   │  │           │     ├─ UserDetailsImpl.java
   │  │           │     └─ UserDetailsServiceImpl.java
   │  │           └─ web
   │  │              └─ controller
   │  │                 ├─ LoginController.java
   │  │                 └─ ProdutoController.java
   │  └─ resources
   │     ├─ application-dev.yml
   │     ├─ application-prod.yml
   │     ├─ application.yaml
   │     └─ data.sql
   └─ test
      └─ java
         └─ com
            └─ b4x
               └─ teste
                  └─ TesteApplicationTests.java
```
## Instalação e Execução do Projeto

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- **Java 17**
- **Git** para clonar o repositório

## Passo a Passo

### 1. Clone o Repositório

```bash
git clone https://github.com/wellinton-lucas/b4x-teste.git
```

### 2. Acesse o Diretório do Projeto

```bash
cd b4x-teste
```

### 3. Compile e Instale as Dependências

```bash
./mvnw clean install
```

> **Nota para Windows:** Use `mvnw.cmd clean install` ao invés de `./mvnw clean install`

### 4. Execute o Projeto

```bash
./mvnw spring-boot:run
```

> **Nota para Windows:** Use `mvnw.cmd spring-boot:run` ao invés de `./mvnw spring-boot:run`

### 5. Acesse a Aplicação

Após a inicialização bem-sucedida, a aplicação estará disponível em:

- **API:** http://localhost:8080
- **Console H2 Database:** http://localhost:8080/h2-console
- **Actuator:** http://localhost:8080/actuator
> **Observação:** Você precisa estar logado antes de tudo, para como logar veja a sessão de **Documentação da API**.
---

## Configuração do Banco de Dados H2

O projeto utiliza o banco de dados H2 em memória para testes. Para acessar o console do banco:

1. Acesse: http://localhost:8080/h2-console
2. Configure a conexão:
   - **JDBC URL:** `jdbc:h2:mem:dev`
   - **User Name:** `admin`
   - **Password:** *1234*
3. Clique em **Connect**

> ⚠️ **Importante:** O banco H2 é um banco de dados em memória, portanto **não persiste os dados após a reinicialização** da aplicação.

---

## Usuários Pré-configurados

O sistema já vem com dois usuários criados para facilitar os testes:

### Usuário Normal
- **Email:** user@gmail.com
- **Senha:** 1234
- **Permissões:** Acesso aos endpoints básicos de produtos

### Usuário Admin
- **Email:** admin@gmail.com
- **Senha:** 1234
- **Permissões:** Acesso completo, incluindo endpoints administrativos (`/actuator`)

---

## Testando a API

### Recomendação: Use o Postman

1. **Faça o login** primeiro usando o endpoint `/login`
2. **Copie o token JWT** retornado na resposta
3. **Configure o token** no Postman:
   - Vá em **Authorization**
   - Selecione **Bearer Token**
   - Cole o token recebido
4. **Faça as requisições** aos demais endpoints
## Documentação da API

## Autenticação

### Realiza o login na API

```http
POST /login
```

| Parâmetro  | Tipo     | Descrição                                    |
| :--------- | :------- | :------------------------------------------- |
| `email`    | `string` | **Obrigatório**. O email do usuário          |
| `password` | `string` | **Obrigatório**. A senha do usuário          |

**Exemplo de Request Body:**
```json
{
  "email": "user@gmail.com",
  "password": "1234"
}
```

**Exemplo de Response (Sucesso - 200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Possíveis Respostas de Erro:**
- `401 Unauthorized`: Credenciais inválidas
- `404 Not Found`: Usuário não encontrado
- `500 Internal Server Error`: Erro interno do servidor

---

## Endpoints de Produtos

### Listar todos os produtos

Retorna todos os produtos cadastrados no sistema.

```http
GET /produtos
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "publicId": "123e4567-e89b-12d3-a456-426614174000",
    "nome": "Produto Exemplo",
    "descricao": "Descrição do produto",
    "preco": 99.90,
    "imgUrl": "https://exemplo.com/imagem.jpg",
    "categoria": "ELETRONICOS",
    "dataCriacao": "2024-01-15T10:30:00",
    "dataUltimaModificacao": "2024-01-15T10:30:00"
  }
]
```

---

### Buscar produtos por critérios

Permite buscar produtos usando filtros opcionais.

```http
GET /produtos/search
```

| Parâmetro    | Tipo     | Descrição                                        |
| :----------- | :------- | :----------------------------------------------- |
| `publicId`   | `UUID`   | **Opcional**. ID público do produto              |
| `nome`       | `string` | **Opcional**. Nome do produto                    |
| `descricao`  | `string` | **Opcional**. Descrição do produto               |
| `preco`      | `double` | **Opcional**. Preço do produto                   |
| `categoria`  | `enum`   | **Opcional**. Categoria do produto               |

**Exemplo de Request:**
```http
GET /produtos/search?nome=Notebook&categoria=ELETRONICOS
```

**Resposta de Sucesso (200 OK):**
```json
[
  {
    "publicId": "123e4567-e89b-12d3-a456-426614174000",
    "nome": "Notebook",
    "descricao": "Notebook de alta performance",
    "preco": 3500.00,
    "imgUrl": "https://exemplo.com/notebook.jpg",
    "categoria": "ELETRONICOS",
    "dataCriacao": "2024-01-15T10:30:00",
    "dataUltimaModificacao": "2024-01-15T10:30:00"
  }
]
```

**Possíveis Respostas:**
- `200 OK`: Produtos encontrados
- `404 Not Found`: Nenhum produto encontrado com os critérios informados

---

### Buscar produto por ID

Retorna um produto específico pelo seu ID público.

```http
GET /produtos/{id}
```

| Parâmetro | Tipo   | Descrição                                    |
| :-------- | :----- | :------------------------------------------- |
| `id`      | `UUID` | **Obrigatório**. O ID público do produto     |

**Resposta de Sucesso (200 OK):**
```json
{
  "publicId": "123e4567-e89b-12d3-a456-426614174000",
  "nome": "Produto Exemplo",
  "descricao": "Descrição do produto",
  "preco": 99.90,
  "imgUrl": "https://exemplo.com/imagem.jpg",
  "categoria": "ELETRONICOS",
  "dataCriacao": "2024-01-15T10:30:00",
  "dataUltimaModificacao": "2024-01-15T10:30:00"
}
```

---

### Criar produto

Cria um novo produto no sistema.

```http
POST /produtos
```

**Request Body:**

| Campo        | Tipo     | Descrição                                        |
| :----------- | :------- | :----------------------------------------------- |
| `nome`       | `string` | **Obrigatório**. Nome do produto (máx. 64 caracteres) |
| `descricao`  | `string` | **Opcional**. Descrição do produto (máx. 124 caracteres) |
| `preco`      | `double` | **Obrigatório**. Preço do produto                |
| `imgUrl`     | `string` | **Opcional**. URL da imagem do produto (máx. 1024 caracteres) |
| `categoria`  | `enum`   | **Obrigatório**. Categoria do produto            |

**Exemplo de Request:**
```json
{
  "nome": "Smartphone XYZ",
  "descricao": "Smartphone com 128GB de armazenamento",
  "preco": 1999.90,
  "imgUrl": "https://exemplo.com/smartphone.jpg",
  "categoria": "ELETRONICOS"
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "publicId": "123e4567-e89b-12d3-a456-426614174000",
  "nome": "Smartphone XYZ",
  "descricao": "Smartphone com 128GB de armazenamento",
  "preco": 1999.90,
  "imgUrl": "https://exemplo.com/smartphone.jpg",
  "categoria": "ELETRONICOS",
  "dataCriacao": "2024-01-15T10:30:00",
  "dataUltimaModificacao": "2024-01-15T10:30:00"
}
```

---

### Atualizar produto (parcial)

Atualiza parcialmente um produto existente. Apenas os campos enviados serão atualizados.

```http
PATCH /produtos
```

**Request Body:**

| Campo        | Tipo     | Descrição                                        |
| :----------- | :------- | :----------------------------------------------- |
| `publicId`   | `UUID`   | **Obrigatório**. ID público do produto a ser atualizado |
| `nome`       | `string` | **Opcional**. Novo nome do produto               |
| `descricao`  | `string` | **Opcional**. Nova descrição do produto          |
| `preco`      | `double` | **Opcional**. Novo preço do produto              |
| `imgUrl`     | `string` | **Opcional**. Nova URL da imagem                 |
| `categoria`  | `enum`   | **Opcional**. Nova categoria do produto          |

**Exemplo de Request:**
```json
{
  "publicId": "123e4567-e89b-12d3-a456-426614174000",
  "preco": 1799.90
}
```

**Resposta de Sucesso (200 OK):**
```json
{
  "publicId": "123e4567-e89b-12d3-a456-426614174000",
  "nome": "Smartphone XYZ",
  "descricao": "Smartphone com 128GB de armazenamento",
  "preco": 1799.90,
  "imgUrl": "https://exemplo.com/smartphone.jpg",
  "categoria": "ELETRONICOS",
  "dataCriacao": "2024-01-15T10:30:00",
  "dataUltimaModificacao": "2024-01-15T14:45:00"
}
```

> **Nota:** O campo `dataUltimaModificacao` é atualizado automaticamente.

---

### Deletar produto

Remove um produto do sistema.

```http
DELETE /produtos/delete/{id}
```

| Parâmetro | Tipo   | Descrição                                    |
| :-------- | :----- | :------------------------------------------- |
| `id`      | `UUID` | **Obrigatório**. O ID público do produto a ser deletado |

**Resposta de Sucesso (200 OK):**
```
Sem conteúdo no corpo da resposta
```

---

## Categorias Disponíveis

As categorias disponíveis para produtos são definidas pelo enum `CATEGORIA`:

- `TESTE1`
- `TESTE2`

> **Observação sobre imagens:** Normalmente eu uso um serviço de armazenamento como Amazon S3 para armazenar imagens, sendo mais barato. O campo `imgUrl` armazena apenas o link para a imagem.
