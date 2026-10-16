# API Agendador de Tarefas

API REST desenvolvida em **Java com Spring Boot** para gerenciamento e agendamento de tarefas. O projeto permite cadastrar, consultar, atualizar e excluir tarefas, utilizando autenticação JWT, persistência de dados com MongoDB e comunicação entre microsserviços por meio do OpenFeign.

## 🚀 Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data MongoDB
- Spring Security
- JWT (JSON Web Token)
- MongoDB
- Spring Cloud OpenFeign
- Lombok
- MapStruct
- Docker
- Gradle

## 📌 Funcionalidades

- Cadastro de tarefas
- Consulta de tarefas por usuário autenticado
- Consulta de tarefas por período
- Atualização de tarefas
- Alteração de status das tarefas
- Exclusão de tarefas
- Associação das tarefas ao e-mail do usuário
- Autenticação e proteção de endpoints com JWT
- Comunicação com a API Usuário utilizando OpenFeign
- Gerenciamento do status de notificação das tarefas

## 🏗️ Estrutura do projeto

```text
src/main/java/com/javanauta/agendadortarefas

├── controller
│   └── Endpoints da API
│
├── business
│   ├── Services
│   ├── DTOs
│   └── Converters
│
└── infrastructure
    ├── Entities
    ├── Repositories
    ├── Clients
    └── Security
```

A aplicação utiliza arquitetura em camadas, separando as responsabilidades de recebimento de requisições, regras de negócio, persistência de dados e comunicação externa.

## 🔐 Autenticação

A API utiliza autenticação baseada em JWT, integrada ao microsserviço de Usuário.

**Fluxo:**

1. O usuário realiza login na API Usuário.
2. A API Usuário retorna um token JWT.
3. O token é enviado nas requisições protegidas do Agendador de Tarefas.
4. A aplicação valida a autenticação e identifica o usuário.
5. As tarefas são associadas ao e-mail extraído do token.

**Header de autenticação:**

```http
Authorization: Bearer TOKEN
```

## 📍 Endpoints

### Criar tarefa

```http
POST /tarefas
```

Exemplo de requisição:

```json
{
  "nomeTarefa": "Estudar Spring Boot",
  "descricao": "Revisar autenticação JWT e microsserviços",
  "dataEvento": "20-10-2026 14:00:00"
}
```

Necessário JWT.

### Buscar tarefas do usuário

```http
GET /tarefas
```

Retorna as tarefas associadas ao usuário autenticado.

Necessário JWT.

### Buscar tarefas por período

```http
GET /tarefas/eventos?dataInicial=2026-10-20T00:00:00&dataFinal=2026-10-30T23:59:59
```

Retorna as tarefas agendadas dentro do intervalo informado.

### Atualizar tarefa

```http
PUT /tarefas
```

Permite atualizar as informações de uma tarefa cadastrada.

### Alterar status

```http
PATCH /tarefas
```

Permite alterar o status de uma tarefa, conforme as regras implementadas na aplicação.

### Excluir tarefa

```http
DELETE /tarefas/{id}
```

Remove uma tarefa pelo identificador.

## 🗄️ Banco de dados

A aplicação utiliza MongoDB para armazenamento das tarefas.

**Banco de dados:**

```text
db_agendador
```

**Collection:**

```text
tarefas
```

Cada documento representa uma tarefa e contém informações como nome, descrição, data do evento, e-mail do usuário e status da notificação.

## 🔗 Integração entre microsserviços

O Agendador de Tarefas integra-se à API Usuário para realizar operações relacionadas à autenticação e validação dos usuários.

A comunicação entre os serviços utiliza **Spring Cloud OpenFeign**, permitindo requisições HTTP entre aplicações Spring Boot.

**Arquitetura simplificada:**

```text
Cliente
   |
   v
API Agendador de Tarefas
   |
   +-- Spring Security / JWT
   |
   +-- Service
   |      |
   |      +-- Repository
   |      |      |
   |      |      +-- MongoDB
   |      |
   |      +-- OpenFeign
   |             |
   |             +-- API Usuário
```

## ⚙️ Como executar o projeto

### Pré-requisitos

- Java 17+
- MongoDB
- Gradle
- API Usuário em execução

### Configuração

No arquivo `application.properties`, configure:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/db_agendador
usuario.url=http://localhost:8081
server.port=8080
```

### Executar aplicação

**Linux/Mac:**

```bash
./gradlew bootRun
```

**Windows:**

```powershell
.\gradlew.bat bootRun
```

A API será iniciada em:

```text
http://localhost:8080
```

## 🐳 Docker

Para criar a imagem:

```bash
docker build -t agendador-tarefas .
```

Para executar o container:

```bash
docker run -p 8080:8080 agendador-tarefas
```

Ao utilizar Docker, as URLs de conexão com o MongoDB e com a API Usuário devem ser configuradas de acordo com a rede dos containers.

## 👩‍💻 Desenvolvimento

Projeto desenvolvido para estudo e prática de:

- Desenvolvimento de APIs REST
- Arquitetura em camadas
- Persistência de dados NoSQL
- Autenticação com JWT
- Comunicação entre microsserviços
- Integração com OpenFeign
- Operações CRUD
- Manipulação de datas e eventos
- Boas práticas de desenvolvimento Back-end com Java

## 🔗 Projetos relacionados

**API Usuário:**  
https://github.com/lari-dias/usuario

**API Notificação:**  
https://github.com/lari-dias/notificacao

## 📄 Licença

Consulte o repositório para verificar as condições de licenciamento do projeto.
