# 📦 Estoque API

API REST para controle de estoque de produtos desenvolvida com Spring Boot.

## 🚀 Tecnologias

- Java 21
- Spring Boot 3
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Maven

## ▶️ Como rodar

**Pré-requisitos:** Java 21 instalado

```bash
# Clonar o repositório
git clone https://github.com/ronizera/estoque-java.git

# Entrar na pasta
cd estoque-spring

# Rodar o projeto
./mvnw spring-boot:run
```

A API vai rodar em `http://localhost:8081`

Para visualizar o banco de dados acesse `http://localhost:8081/h2-console`

## 🗄️ Banco de dados

Para visualizar o banco acesse `http://localhost:8081/h2-console` com as credenciais:

- **JDBC URL:** jdbc:h2:mem:estoque
- **User:** sa
- **Password:** (deixa vazio)


## 📋 Endpoints

### Categorias

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /categorias | Lista todas as categorias |
| GET | /categorias/{id} | Busca uma categoria |
| POST | /categorias | Cria uma categoria |
| DELETE | /categorias/{id} | Remove uma categoria |

### Produtos

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /produtos | Lista todos os produtos |
| GET | /produtos/{id} | Busca um produto |
| POST | /produtos | Cria um produto |
| DELETE | /produtos/{id} | Remove um produto |

### Estoque

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /estoque | Lista estoque atual de todos os produtos |
| GET | /estoque/{produtoId} | Busca estoque de um produto |
| GET | /estoque/historico | Lista histórico de movimentações |
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