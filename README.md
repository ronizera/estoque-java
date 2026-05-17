# 📦 Estoque API

API REST para controle de estoque de produtos desenvolvida com Spring Boot. Permite gerenciar categorias, produtos e movimentações de entrada e saída do estoque.

## 🚀 Tecnologias

- Java 21
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA + Hibernate
- H2 Database
- Lombok
- Swagger / OpenAPI
- Maven

## ▶️ Como rodar

**Pré-requisitos:** Java 21 instalado

```bash
# Clonar o repositório
git clone https://github.com/ronizera/estoque-spring.git

# Entrar na pasta
cd estoque-spring

# Copiar o arquivo de configuração
cp src/main/resources/application-example.properties src/main/resources/application.properties

# Rodar o projeto
./mvnw spring-boot:run
```

A API vai rodar em `http://localhost:8081`

## 📄 Documentação

Acesse a documentação interativa em `http://localhost:8081/swagger-ui.html`

Para testar rotas protegidas:
1. Faça o registro em `POST /auth/register`
2. Faça o login em `POST /auth/login` e copie o token sem as aspas ""
3. Clique em **Authorize** e cole o token

## 🗄️ Banco de dados

Acesse o console do H2 em `http://localhost:8081/h2-console`
JDBC URL: jdbc:h2:mem:estoque
User:     sa
Password: (vazio)


## 📋 Endpoints

### Auth
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | /auth/register | Cadastra novo usuário |
| POST | /auth/login | Realiza login e retorna token |

### Categorias
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /categorias | Lista todas as categorias |
| GET | /categorias/{id} | Busca uma categoria |
| POST | /categorias | Cria uma categoria |
| PUT | /categorias/{id} | Atualiza uma categoria |
| DELETE | /categorias/{id} | Remove uma categoria |

### Produtos
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /produtos | Lista todos os produtos |
| GET | /produtos/{id} | Busca um produto |
| POST | /produtos | Cria um produto |
| PUT | /produtos/{id} | Atualiza um produto |
| DELETE | /produtos/{id} | Remove um produto |

### Estoque
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /estoque | Estoque atual de todos os produtos |
| GET | /estoque/{produtoId} | Estoque de um produto específico |
| GET | /estoque/historico | Histórico de movimentações |
| POST | /estoque/entrada | Registra entrada no estoque |
| POST | /estoque/saida | Registra saída do estoque |

## 📝 Exemplos de uso

**Criar categoria:**
```json
POST /categorias
{
  "nome": "Eletrônicos",
  "descricao": "Produtos eletrônicos em geral"
}
```

**Criar produto:**
```json
POST /produtos
{
  "nome": "Notebook Dell",
  "preco": 3500.00,
  "categoriaId": 1
}
```

**Entrada no estoque:**
```json
POST /estoque/entrada
{
  "produtoId": 1,
  "quantidade": 10
}
```

**Saída do estoque:**
```json
POST /estoque/saida
{
  "produtoId": 1,
  "quantidade": 3
}
```

## 🏗️ Estrutura do projeto
src/main/java/com/roni/estoque/
auth/           → autenticação JWT e Spring Security
categoria/      → gerenciamento de categorias
produto/        → gerenciamento de produtos
estoque/        → controle de movimentações
exception/      → tratamento global de erros
config/         → configuração do Swagger

## 🔒 Segurança

- Autenticação via JWT com expiração de 24 horas
- Senhas criptografadas com BCrypt
- Rotas protegidas com Spring Security