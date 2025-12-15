# 📚 Bookstore API

**Autores:** Arthur Cechinel Nunes, Raul Vicente Gelsleichter

---

## 📝 Descrição do Projeto

A **Bookstore API** é um sistema RESTful desenvolvido em Java com Spring Boot, seguindo boas práticas de arquitetura e organização em camadas. Ela permite gerenciar livros, autores e categorias, oferecendo endpoints completos para CRUD e consultas personalizadas.

Este projeto foi desenvolvido como atividade avaliativa seguindo todos os critérios solicitados pelo professor.

---

## 🚀 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 4** (Web, Validation, JPA)
* **Spring Security** (autenticação via HTTP Basic ou JWT – conforme implementação no trabalho)
* **Spring Data JPA / Hibernate**
* **Flyway** (migrations)
* **H2/PostgreSQL/MySQL** (dependendo da configuração do grupo)
* **Lombok** (opcional)

---

## ✔️ Requisitos Atendidos

| Critério                                              | Atendido |
| ----------------------------------------------------- | -------- |
| API integrável com frontend                           | ✔️       |
| CRUD completo                                         | ✔️       |
| Consulta por campo (título)                           | ✔️       |
| Métodos e parâmetros corretos                         | ✔️       |
| Uso de DTOs                                           | ✔️       |
| Camadas organizadas (Controller, Service, Repository) | ✔️       |
| Entidades corretas com relacionamentos                | ✔️       |
| Mecanismo de segurança                                | ✔️       |
| Documentação                                          | ✔️       |
| Migrations (Flyway)                                   | ✔️       |

---

## Regras de negócio


* **Regra 1 - Um livro deve pertencer a uma categoria existente**
* **Regra 2 - Autores associados ao livro devem existir**
* **Regra 3 - Um livro pode ter múltiplos autores**
* **Regra 4 - Não é possível atualizar ou deletar um livro inexistente**
* **Regra 5 - Apenas campos enviados podem ser alterados**
* **Regra 6 - A busca por título é parcial e não sensível a maiúsculas**


## 🧱 Arquitetura do Projeto

```
com.bookstore.api
├── controller      # Endpoints REST
├── service         # Regras de negócio
│   └── impl
├── repository      # Acesso ao banco
├── dto             # Objetos de transferência
├── entity          # Entidades JPA
├── exception       # Tratamento de erros
├── mapper          # Conversores entre Entity e DTO
└── config          # Segurança, CORS, etc
```

---

## 📦 Endpoints Principais

### 📘 Books

```
POST /api/books               # Criar livro
PUT /api/books/{id}           # Atualizar livro
DELETE /api/books/{id}        # Deletar
GET /api/books/{id}           # Buscar por ID
GET /api/books                # Listar todos
GET /api/books?title=xxxxx    # Buscar por título
```

### 🧑‍💼 Authors

```
POST /api/authors
GET /api/authors
...
```

### 🏷️ Categories

```
POST /api/categories
GET /api/categories
...
```

---

## 🔎 Exemplo de Busca por Título

**Requisição:**

```
GET /api/books?title=senhor
```

**Resposta:**

```json
[
  {
    "id": 2,
    "title": "O Senhor dos Anéis",
    "isbn": "12345",
    "price": 59.90,
    "categoryId": 1,
    "categoryName": "Ficção",
    "authorIds": [1, 2],
    "authorNames": ["Autor A", "Autor B"]
  }
]
```

---

## 🔐 Segurança

O sistema utiliza **Spring Security**, com autenticação configurada via:

* HTTP Basic **ou**
* JWT (dependendo da versão implementada pelo grupo)

Rotas protegidas exigem login.

---

## 🗃️ Migrations (Flyway)

O projeto utiliza migrations para criar tabelas, inserir dados iniciais, configurar relacionamentos e manter a estrutura do banco sincronizada.

Exemplo:

```
V1__create_tables.sql
V2__insert_data.sql
```

---

## ▶️ Como Rodar o Projeto

### 1. Clonar o repositório

```
git clone https://github.com/seu-repositorio/bookstore-api.git
```

### 2. Rodar com Maven

```
mvn spring-boot:run
```

### 3. Acessar API

```
http://localhost:8080/api/books
```

---

## 📖 Documentação da API

* Se usando **Springdoc**: acessar

```
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```

---

## 👥 Integrantes do Grupo

* **Arthur Cechinel Nunes**
* **Raul Vicente Gelsleichter**